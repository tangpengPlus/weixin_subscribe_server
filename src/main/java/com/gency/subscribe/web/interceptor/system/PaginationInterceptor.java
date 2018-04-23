package com.gency.subscribe.web.interceptor.system;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.BaseUtil;
/**
 * 
 * 作者:唐鹏
 * 描述: 分页处理拦截器
 * 版本: version 1.0.0
 * 时间: 2017年8月29日 下午2:22:40
 */
public class PaginationInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String reqParm=BaseUtil.getHttpRequestParm(request);
        if(!StringUtils.isEmpty(reqParm)){
      	  request.setAttribute("curryRequestUrl", request.getRequestURI()+"?"+reqParm);
        }else{
      	  request.setAttribute("curryRequestUrl", request.getRequestURI()+"?"); 
        }
        request.setAttribute("curryRequestUrlforSys", request.getRequestURI());
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
