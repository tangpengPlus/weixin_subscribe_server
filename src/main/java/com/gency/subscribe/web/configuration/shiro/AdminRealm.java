package com.gency.subscribe.web.configuration.shiro;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import com.gency.subscribe.core.constant.base.SystemConstant;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.service.system.AdminService;
import com.gency.subscribe.service.system.AuthorityService;
import com.gency.subscribe.service.system.EncryptionService;
import com.gency.subscribe.service.system.ImageService;

/**
 * 
 * 作者:唐鹏
 * 描述: shiro权限管理数据源配置
 * 版本: version 1.0.0
 * 时间: 2017年7月24日 下午4:56:17
 */
public class AdminRealm extends AuthorizingRealm{
 
	 private static final Log log=LogFactory.getLog(AdminRealm.class);
	
	@Autowired
	@Lazy
	private AdminService adminService;
	@Autowired
	@Lazy
	private AuthorityService authorityService;
	@Autowired
	@Lazy
	private EncryptionService encryptionService;
	@Autowired
	@Lazy
	private ImageService imageService;
	@Value("${spring.explorationModel}")
	private boolean explorationModel;
	/**
	 * 认证信息.(身份验证) : Authentication 是用来验证用户身份
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*获取当前登录时的用户信息*/
		 ManageAdmin admin=(ManageAdmin) principals.getPrimaryPrincipal();
		 /*获取当前登录管理员的信息*/
		 /*初始化认证信息*/
		 SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		 /*获取当前登录管理员的权限信息*/
		 try {
			final List<ManageAuthority> authorities=authorityService.selectCurryLoginAdminManageAuthority(admin.getId());
			for(ManageAuthority authority:authorities){
				authorizationInfo.addStringPermission(authority.getAuthorityurl());
			}
		} catch (Exception e) {
			log.error("----------权限入库失败(查询权限信息出错)-----------", e);
			e.printStackTrace();
		}
		return authorizationInfo;
	}
    /**
     * 登录验证
     */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AdminLoginToken adminLoginToken=(AdminLoginToken) token;
		 String username="";
		 String password="";
		if(explorationModel){
			 //构造登录验证码
			adminLoginToken.getRequest().getSession().setAttribute(SystemConstant.VERIFICATION_CODE,"GENCY");
			username="admin";
			password="123456";
		}else{
			 username=getLoginName(adminLoginToken.getUsername(), adminLoginToken.getRequest());
	         password=getPassword(String.valueOf(adminLoginToken.getPassword()),  adminLoginToken.getRequest());
				
		}
        log.info("验证用户【"+username+"】登录开始");
		if(StringUtils.isEmpty(username)){
			log.info("登录用户名为空");
			throw new UnknownAccountException("用户名或者密码错误");
		}
		if(username.getBytes().length>12||username.getBytes().length<4){
			log.info("登录用户【"+username+"】名不合法");
			throw new UnknownAccountException("用户名或者密码错误");
		}
		//判断当前验证码是否正确
		if(!imageService.isMatchingVerificationCode(adminLoginToken.getCaptcha(), adminLoginToken.getRequest().getSession())){
			log.info("登录用户【"+username+"】验证码错误");
			throw new UnsupportedTokenException("登录验证码错误");
		}
		/*获取管理员信息*/
         ManageAdmin admin=new ManageAdmin();
         admin.setLoginname(username);
         try {
			admin=adminService.selectOne(admin);
		} catch (Exception e) {
			log.error("根据用户名【"+username+"】查询用户信息出错", e);
			e.printStackTrace();
		}
		if(admin==null){
			 log.info("登录失败：登录账户【"+username+"】不存在");
			throw new UnknownAccountException("用户名或者密码错误");
		}
		if(admin.getState()!=null&&admin.getState().intValue()==1){
			log.info("登录失败：登录账户【"+username+"】被限制登录");
			throw new LockedAccountException("登录失败：登录账户【"+username+"】被限制登录");
		}
         /*验证管理员数据*/
		adminLoginToken.setUsername(username);
		adminLoginToken.setPassword(password.toCharArray());
		return new SimpleAuthenticationInfo(admin,password,ByteSource.Util.bytes(admin.getSalt()), getName());
		
	}
    /**
     * 
     * 作者:唐鹏
     * 描述: 解密用户名
     * 版本: version 1.0.0
     * 时间: 2017年8月2日 上午11:46:55
     * @param userName
     * @return
     * String
     */
    public String getLoginName(String userName,HttpServletRequest request){
    	if(StringUtils.isNotEmpty(userName)){
    		String name=encryptionService.decryptParameter(userName, request);
    	return name;
    	}
    	return "";
    }
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 解密密码
     * 版本: version 1.0.0
     * 时间: 2017年8月2日 上午11:53:12
     * @param password
     * @return
     * String
     */
    public String getPassword(String password,HttpServletRequest request){
    	if(StringUtils.isNotEmpty(password)){
    		String encrypPassword=encryptionService.decryptParameter(password, request);
    		encryptionService.removePrivateKey(request);
    		return encrypPassword;
    	}
    	return "";
    }
}
