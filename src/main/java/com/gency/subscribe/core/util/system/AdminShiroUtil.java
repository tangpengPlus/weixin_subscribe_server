package com.gency.subscribe.core.util.system;
import org.apache.commons.logging.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.gency.subscribe.model.system.ManageAdmin;

import org.apache.commons.logging.LogFactory;

/**
 * 
 * 作者:唐鹏
 * 描述: 系统安全框架shiro工具
 * 版本: version 1.0.0
 * 时间: 2017年7月31日 下午2:16:10
 */
public class AdminShiroUtil {
	
	private static final Log log=LogFactory.getLog(AdminShiroUtil.class);
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前系统授权对象
	 * 版本: version 1.0.0
	 * 时间: 2017年7月31日 下午2:16:47
	 * @return
	 * Subject
	 */
	public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前会话
	 * 版本: version 1.0.0
	 * 时间: 2017年7月31日 下午2:17:24
	 * @return
	 * Session
	 */
	public static Session getSession(){
        try{
            Session session = getSubject().getSession();
            if (session == null){
                session = getSubject().getSession();
            }
            if (session != null){
                return session;
            }
        }catch (InvalidSessionException e){
            e.printStackTrace();
        }
        return null;
    }
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前登录管理员信息
	 * 版本: version 1.0.0
	 * 时间: 2017年7月31日 下午2:18:14
	 * @return
	 * ManageAdmin
	 */
	public static ManageAdmin getUserInfo(){
        try {
            if(getSession() != null){
            	ManageAdmin admin = (ManageAdmin) getSubject().getPrincipals().getPrimaryPrincipal();
                return admin;
            }else{
                return null;
            }
        }catch (Exception e){
        	log.error("获取当前操作管理员信息失败", e);
        }
        return null;
    }
}
