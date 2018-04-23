package com.gency.subscribe.web.filter.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Value;

import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.web.configuration.shiro.AdminLoginToken;
/**
 * 
 * 作者:唐鹏
 * 描述:权限认证过滤 所有请求将会先进行此filter 进行令牌生成
 * 版本: version 1.0.0
 * 时间: 2017年1月19日 上午10:09:04
 */
public class AuthenticationFilter extends FormAuthenticationFilter{
	@Value("${spring.explorationModel}")
	private boolean explorationModel;
	
	 /** 登录成功URL */
    public static final String SUCCESS_URL = "successUrl";

    /** 默认"用户"参数名称 */
    public static final String DEFAULT_USERNAME_PARAM = "username";

    /** 默认"密码"参数名称 */
    private static final String DEFAULT_PASSWORD_PARAM = "password";

    /** 默认"验证"参数名称 */
    private static final String DEFAULT_CAPTCHA_PARAM = "captcha";

    /** 默认"记住"参数名称 */
    public static final String DEFAULT_REMEMBER_ME_PARAM = "rememberMe";

    /** "用户"参数名称 */
    private String usernameParam = DEFAULT_USERNAME_PARAM;

    /** "密码"参数名称 */
    private String passwordParam = DEFAULT_PASSWORD_PARAM;

    /** "验证"参数名称 */
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;

    /** "记住"参数名称 */
    private String rememberMeParam = DEFAULT_REMEMBER_ME_PARAM;
    
    private String time_out_show_url;//身份验证超时展示页面
    
    /**
     * 获取令牌
     */
    @Override
    protected org.apache.shiro.authc.AuthenticationToken createToken(ServletRequest servletRequest,
            ServletResponse servletResponse) {
        String username = getUsername(servletRequest);
        String password = getPassword(servletRequest);
        String captcha = getCaptcha(servletRequest);
        boolean rememberMe = isRememberMe(servletRequest);
        String host = getHost(servletRequest); 
        return new AdminLoginToken(username, password, captcha, rememberMe, host, (HttpServletRequest)servletRequest);
    }
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
    	Subject currentUser = SecurityUtils.getSubject();   
    	if(explorationModel&&!currentUser.isAuthenticated()){//如果是开发者模式
	    	   //RSA用户名
	    	   //String userName="mQ1ZM7Ja2XJflhFUEOJLpNgOj3NuvvDmE1v/R5DgfJK6d3lLO2dprvkeebdXdYKHk1uYBBcxAtYiVg6hOD1eG3atE45PmXZUAn/DYof+gT7gr0+tcWHgDRII7hUyrmbN2sQPCP00uwwSxas4GT1Cov9HjvQqWNHYI7UBu97EMP8=";
	    	   //RSA用户密码
	    	   //String password="TX9iXFvV9HFb8zabvB8/sihWLEHbakvpmuXs/Ftg/9bPK+iQqqB+0LiWwO1jPOW5/+fsfZZarSro5tEcJfTAsa6DrUquanrMKfOeEK6IyyvC3K7P/hWHu3iA8aiw4L82UzRq9trKK4grwK6zehOgKWuBZs3HTHdA4vceTkhYQkA=";
	    	   //构造超级管理员令牌
	    	   AdminLoginToken loginToken=new AdminLoginToken("admin", "123456", "GENCY",false, "127.0.0.1", (HttpServletRequest)servletRequest);
	    	   /*使用当前账号密码进行系统登录操作*/
			   currentUser.login(loginToken);
	    	   return true;
	    }
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response =(HttpServletResponse) servletResponse;
        String requestType = request.getHeader("X-Requested-With");
        // 判断是否为登录请?
        if (isLoginRequest(request, response)) {
            // 判断是否包含登录参数
            if (isLoginSubmission(request, response)) {
                // 判断是否为AJAX请求（请求方式：AJAX异步请求?
                if (StringUtils.equalsIgnoreCase(requestType, "XMLHttpRequest")) {
                    return executeLogin(request, response);
                }
                // 限制访问
                response.addHeader("loginStatus", "Access Denied");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            }
            return true;
        }
        // 保存请求、重定向到登录
        if(request.getSession()==null){
        	WebUtils.redirectToSavedRequest(request, response, time_out_show_url);
        	return false;
        }
        ManageAdmin admin=(ManageAdmin) request.getSession().getAttribute("admin");
        if(admin==null){//当前身份验证信息超时
        	WebUtils.redirectToSavedRequest(request, response, time_out_show_url);
        	return false;
        }
        saveRequestAndRedirectToLogin(request, response);
        return false;
    }
    @Override
    protected boolean onLoginSuccess(org.apache.shiro.authc.AuthenticationToken token, Subject subject,
            ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
       /*// 防止Session Fixation攻击
        Session session = subject.getSession();
        // 销毁Session
        Map<Object, Object> attributes = new HashMap<Object, Object>();
        Collection<Object> keys = session.getAttributeKeys();
        for (Object key : keys) {
            attributes.put(key, session.getAttribute(key));
        }
        session.stop();
        // 重构Session
        session = subject.getSession();
        for (Entry<Object, Object> entry : attributes.entrySet()) {
            session.setAttribute(entry.getKey(), entry.getValue());
        }
        // 设置登录成功跳转URL
        SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(servletRequest);
        if (savedRequest != null && StringUtils.equalsIgnoreCase(savedRequest.getMethod(), AccessControlFilter.GET_METHOD)) {
            servletRequest.setAttribute(SUCCESS_URL, savedRequest.getRequestUrl());
        } else {
            servletRequest.setAttribute(SUCCESS_URL, getSuccessUrl());
        }*/

        return true;
    }
    /**
     * 获取验证码
     * 
     * @param servletRequest
     *            应用请求
     * @return 验证?
     */
    protected String getCaptcha(ServletRequest servletRequest) {
        return WebUtils.getCleanParam(servletRequest, captchaParam);
    }
    /**
     * 获取会话
     * 
     * @param servletRequest
     *            应用请求
     * @return 会话
     */
    protected HttpSession getSession(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return request.getSession();
    }
    
    
    public String getUsernameParam() {
        return usernameParam;
    }

    public void setUsernameParam(String usernameParam) {
        this.usernameParam = usernameParam;
    }

    public String getPasswordParam() {
        return passwordParam;
    }

    public void setPasswordParam(String passwordParam) {
        this.passwordParam = passwordParam;
    }

    public String getRememberMeParam() {
        return rememberMeParam;
    }

    public void setRememberMeParam(String rememberMeParam) {
        this.rememberMeParam = rememberMeParam;
    }

    public String getCaptchaParam() {
        return captchaParam;
    }

    public void setCaptchaParam(String captchaParam) {
        this.captchaParam = captchaParam;
    }
	public String getTime_out_show_url() {
		return time_out_show_url;
	}
	public void setTime_out_show_url(String time_out_show_url) {
		this.time_out_show_url = time_out_show_url;
	}

    
}
