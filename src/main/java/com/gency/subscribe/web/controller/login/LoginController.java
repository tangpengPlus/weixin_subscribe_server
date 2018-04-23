package com.gency.subscribe.web.controller.login;

import java.security.interfaces.RSAPublicKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.constant.message.AuthenticationMessage;
import com.gency.subscribe.core.constant.message.Message;
import com.gency.subscribe.service.system.EncryptionService;
import com.gency.subscribe.service.system.ImageService;
/**
 * 
 * 作者:唐鹏
 * 描述:登录控制
 * 版本: version 1.0.0
 * 时间: 2017年7月14日 下午4:44:25
 */
@Controller
@RequestMapping(value="/system/admin")
public class LoginController {
  private static final  Log log=LogFactory.getLog(LoginController.class);
  @Autowired
  private ImageService imageService;
  @Autowired
  private EncryptionService pncryptionService;
  /**
   * 
   * 作者:唐鹏
   * 描述:打开登录页面
   * 版本: version 1.0.0
   * 时间: 2017年7月24日 下午2:22:26
   * @param request
   * @return
   * ModelAndView
   */
  @GetMapping(value="/login")
	public ModelAndView login(HttpServletRequest request,
			@RequestParam(value="loginPageMessage",required=false ,defaultValue="")String loginPageMessage){
		log.info("--------------------打开登录页面--------------------");
		ModelAndView mv=new ModelAndView();
		// 密钥
        RSAPublicKey publicKey = pncryptionService.generateKey(request);
        mv.addObject("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
        mv.addObject("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		if(StringUtils.isNotEmpty(loginPageMessage)){
			mv.addObject("loginPageMessage", loginPageMessage);
		}
		mv.setViewName("login/login");
		return mv;
	}
	
    /**
     * 
     * 作者:唐鹏
     * 描述: 系统登录
     * 版本: version 1.0.0
     * 时间: 2017年7月24日 下午2:34:34
     * @param request
     * @return
     * ModelAndView
     */
  @PostMapping(value="/login")
	public @ResponseBody Message login(HttpServletRequest request){
		Subject user = SecurityUtils.getSubject();
		if(user.isAuthenticated()){
			return	AuthenticationMessage.success("登录成功", "/system/home/index");
		}
		String loginFailureKey = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
		 if (StringUtils.isNotBlank(loginFailureKey)) {
	            if (StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.pam.UnsupportedTokenException")) {
	                return AuthenticationMessage.error("验证码错误", pncryptionService.generateKey(request));
	            } else if (StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.UnknownAccountException")) {
	                return AuthenticationMessage.error("账户或密码错误", pncryptionService.generateKey(request));
	            } else if (StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.DisabledAccountException")) {
	                return AuthenticationMessage.error("账户已被禁用", pncryptionService.generateKey(request));
	            } else if (StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.LockedAccountException")) {
	                return AuthenticationMessage.error("账户已被锁定", pncryptionService.generateKey(request));
	            }else if (StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.IncorrectCredentialsException")) {
	                return AuthenticationMessage.error("账户或密码错误", pncryptionService.generateKey(request));
	            }else if(StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.ExcessiveAttemptsException")){
	            	return AuthenticationMessage.error("错误登录次数过多，请等十分钟后再试", pncryptionService.generateKey(request));
	            }else if(StringUtils.equals(loginFailureKey, "org.apache.shiro.authc.AuthenticationException")){
	            	return AuthenticationMessage.error("账户或密码错误", pncryptionService.generateKey(request));
	            }
		 }
	            return AuthenticationMessage.error("登录失败", pncryptionService.generateKey(request));
	}
  
  
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取登录验证码
	 * 版本: version 1.0.0
	 * 时间: 2017年7月24日 下午2:22:12
	 * @param request
	 * @param response
	 * void
	 */
	@GetMapping(value="/verificationcode")
	public void verificationcode(HttpServletRequest request,HttpServletResponse response){
		log.info("--------------------获取登录验证码--------------------");
		try {
			imageService.getVerificationCode(response, request);
		} catch (Exception e) {
			log.error("获取登录验证码错误", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 退出登录操作
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 下午2:39:50
	 * @param session
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="loginout")
	public ModelAndView loginOut(HttpSession session,ModelAndView mv){
		mv.setViewName("redirect:login");
		session.invalidate();
		Subject subject = SecurityUtils.getSubject();
		if(subject!=null&&subject.isAuthenticated()){
			subject.logout(); 
		}
		return mv;
	}
}
