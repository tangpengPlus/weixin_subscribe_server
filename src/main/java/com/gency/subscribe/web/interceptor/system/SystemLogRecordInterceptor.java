package com.gency.subscribe.web.interceptor.system;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.BaseUtil;
import com.gency.subscribe.core.util.system.AdminShiroUtil;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.ManageOptionLog;
import com.gency.subscribe.service.system.AuthorityService;
import com.gency.subscribe.service.system.SystemLogService;

/**
 * 
 * 作者:唐鹏
 * 描述: 日志记录拦截器(同时判断当前用户是否有该权限)
 * 版本: version 1.0.0
 * 时间: 2017年8月25日 上午9:58:49
 */
public class SystemLogRecordInterceptor implements HandlerInterceptor {
@Autowired
private AuthorityService authorityService;
@Autowired
private SystemLogService systemLogService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 //获取当前请求的url
		 String url = request.getServletPath();
		 ManageAdmin admin = null;
		try {
			admin = AdminShiroUtil.getUserInfo();
		} catch (Exception e) {
		}
		 
		//获取当前操作对象
		if(null==admin){
			return false;
		}
		  //获取当前登录的管理员所有权限信息
		  List<ManageAuthority> authorities=authorityService.selectCurryLoginAdminManageAuthority(admin.getId());
		  for(ManageAuthority authority:authorities){
			  if(authority.getAuthorityurl().equals(url)){
				  //判断当前url是否需要进行日志记录
				  if(authority.getAuthoritylogstate().intValue()==1){
					  if(request.getMethod().equalsIgnoreCase("POST")){
						  ManageOptionLog log=new ManageOptionLog();
						  log.setAdminid(admin.getId());
						  log.setAuthorid(authority.getId());
						  log.setCreatetime(new Date()); 
						  log.setOptioncount(BaseUtil.getHttpRequestParm(request));
						  systemLogService.save(log); 
					  }
				  }
				  return true;
			  }
		  }
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
