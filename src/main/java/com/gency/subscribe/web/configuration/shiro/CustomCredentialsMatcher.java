package com.gency.subscribe.web.configuration.shiro;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;

import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.service.system.EncryptionService;
import com.gency.subscribe.service.system.MenuService;
/**
 * 
 * 作者:唐鹏 描述: 自定义密码验证 版本: version 1.0.0 时间: 2017年7月25日 上午11:57:48
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	private static final Log log=LogFactory.getLog(CustomCredentialsMatcher.class);
	@Autowired
	EncryptionService encryptionService;
	@Autowired
	private MenuService menuService;
	
	//用户登录次数计数  redisKey 前缀
	private String SHIRO_LOGIN_COUNT = "newManage_login_count_";
		
	//用户登录是否被锁定    一小时 redisKey 前缀
	private String SHIRO_IS_LOCK = "newManage_admin_is_lock_";
    
	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		log.info("开始进行密码匹配验证");
		ManageAdmin manageAdmin=(ManageAdmin) info.getPrincipals().getPrimaryPrincipal();
		AdminLoginToken loginToken=(AdminLoginToken) authcToken;
		/*访问一次，计数一次 */
//		ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
//		/*增加计数*/
//		opsForValue.increment(SHIRO_LOGIN_COUNT+manageAdmin.getLoginname(), 1);
//		//计数大于系统设置的登录次数时，设置用户被锁定一小时
//		if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+manageAdmin.getLoginname()))>=SystemConstant.ACCOUNT_LOCK_COUNT){
//			opsForValue.set(SHIRO_IS_LOCK+manageAdmin.getLoginname(), "LOCK");
//			stringRedisTemplate.expire(SHIRO_IS_LOCK+manageAdmin.getLoginname(), 1, TimeUnit.HOURS);
//		} 
//		if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK+manageAdmin.getLoginname()))){
//			log.info("用户【"+manageAdmin.getLoginname()+"】由于尝试登录次数过多已经被禁止登录");
//			throw new DisabledAccountException("由于密码输入错误次数大于"+SystemConstant.ACCOUNT_LOCK_COUNT+"次，帐号已经禁止在一个小时内登录！");
//		}
		/*加密用户输入的密码*/
		try {
			String password=encryptionService.md5Password(loginToken.getUsername(), String.valueOf(loginToken.getPassword()), manageAdmin.getSalt(), 2);
		    if(equals(manageAdmin.getPassword(), password)){
		    	log.info("管理员【"+loginToken.getUsername()+"】登录成功");
		    	loginToken.getRequest().getSession().setAttribute("admin", manageAdmin);
		    	//缓存当前登录管理员的菜单信息
		    	loginToken.getRequest().getSession().setAttribute("curryLoginAdminMenu",menuService.selectCurryLoginAdminMenu(manageAdmin.getId()));
		    	return true;
		    }
		} catch (Exception e) {
			log.error("登录操作:验证用户密码->加密用户输入密码时出错"+loginToken,e);
			e.printStackTrace();
		}
		return false;
	}
}
