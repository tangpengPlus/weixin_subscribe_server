package com.gency.subscribe.service.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 作者:唐鹏
 * 描述: 图片文件服务接口
 * 版本: version 1.0.0
 * 时间: 2017年7月24日 下午2:04:47
 */
public interface ImageService {
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取图片验证码
	 * 版本: version 1.0.0
	 * 时间: 2017年7月24日 下午2:07:23
	 * @param response
	 * @param request
	 * @throws Exception
	 * void
	 */
	void getVerificationCode(HttpServletResponse response,HttpServletRequest request)throws Exception;
    
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 判断验证码是否正确
	 * 版本: version 1.0.0
	 * 时间: 2017年7月24日 下午2:08:34
	 * @param code
	 * @param request
	 * @return
	 * @throws Exception
	 * boolean
	 */
    boolean isMatchingVerificationCode(String code,HttpSession session);

}
