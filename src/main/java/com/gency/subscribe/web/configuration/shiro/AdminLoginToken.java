package com.gency.subscribe.web.configuration.shiro;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authc.UsernamePasswordToken;
/**
 * 
 * 作者:唐鹏
 * 描述: 管理员登录令牌 主要用于封装登录时验证码信息 和会话(session)信息
 * 版本: version 1.0.0
 * 时间: 2017年7月24日 下午5:00:38
 */
public class AdminLoginToken extends UsernamePasswordToken{

	private static final long serialVersionUID = 4944179387614145592L;
	
	

    /** 验证码 */
    private String captcha;

    /** 请求会话 */
    private HttpServletRequest request;
    
    
    /**
     * 构造函数
     * 
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param captcha
     *            验证码
     * @param rememberMe
     *            是否“记住我”
     * @param host
     *            客户端主机
     * @param session
     *            会话
     */
    public AdminLoginToken(String username, String password, String captcha, boolean rememberMe, String host,
            HttpServletRequest request) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.request = request;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


}
