package com.gency.subscribe.web.controller.exception;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.BaseUtil;
import com.gency.subscribe.core.util.system.AdminShiroUtil;
import com.gency.subscribe.model.system.ManageAdmin;
/**
 * 
 * 作者:唐鹏
 * 描述: 全局异常处理
 * 版本: version 1.0.0
 * 时间: 2017年8月3日 下午1:43:50
 */
@Controller
@RequestMapping("/system/error")
public class ErrorPageController {
	private static final Log log=LogFactory.getLog(ErrorPageController.class);
	    /**
	     * 
	     * 作者:唐鹏
	     * 描述: 配置404页面
	     * 版本: version 1.0.0
	     * 时间: 2017年8月3日 下午2:05:19
	     * void
	     */
	    @GetMapping(value="/404")
	    public ModelAndView resourcesNotFount(ModelAndView mv,HttpServletRequest request){
	    	try {
	    		ManageAdmin admin=AdminShiroUtil.getUserInfo();
	    		String requestAdminName="匿名";
	    		if(admin!=null){
	    			requestAdminName=admin.getLoginname();
	    		}
				log.info("访问人:【"+requestAdminName+"】IP【"+BaseUtil.getLoginIP(request).toString()+"】访问资源对象错误代码404");
				mv.addObject("", "");
				mv.setViewName("/base/404");
			} catch (Exception e) {
				log.error("打开【404】页面报错",e);
				e.printStackTrace();
			}
	    	
	    	return mv;
	    }
	    
	    
	    /**
	     * 
	     * 作者:唐鹏
	     * 描述: 定义无效请求的返回页面
	     * 版本: version 1.0.0
	     * 时间: 2017年8月3日 下午2:05:19
	     * void
	     */
	    @GetMapping(value="/400")
	    public ModelAndView invalid(ModelAndView mv,HttpServletRequest request){
	    	try {
	    		ManageAdmin admin=AdminShiroUtil.getUserInfo();
	    		String requestAdminName="匿名";
	    		if(admin!=null){
	    			requestAdminName=admin.getLoginname();
	    		}
				log.info("访问人:【"+requestAdminName+"】IP【"+BaseUtil.getLoginIP(request).toString()+"】访问资源对象错误代码400");
				mv.addObject("", "");
				mv.setViewName("/base/400");
			} catch (Exception e) {
				log.error("打开【400】页面报错",e);
				e.printStackTrace();
			}
	    	
	    	return mv;
	    }
	    
	    
	    /**
	     * 
	     * 作者:唐鹏
	     * 描述: 配置500页面
	     * 版本: version 1.0.0
	     * 时间: 2017年8月3日 下午2:05:19
	     * void
	     */
	    @GetMapping(value="/500")
	    public ModelAndView serverError(ModelAndView mv,HttpServletRequest request){
	    	try {
	    		ManageAdmin admin=AdminShiroUtil.getUserInfo();
	    		String requestAdminName="匿名";
	    		if(admin!=null){
	    			requestAdminName=admin.getLoginname();
	    		}
				log.info("访问人:【"+requestAdminName+"】IP【"+BaseUtil.getLoginIP(request).toString()+"】访问资源对象错误代码500");
				mv.addObject("", "");
				mv.setViewName("/base/500");
			} catch (Exception e) {
				log.error("打开【500】页面报错",e);
				e.printStackTrace();
			}
	    	
	    	return mv;
	    }
	    
	    
	    /**
	     * 
	     * 作者:唐鹏
	     * 描述: 配置403页面(无权限 禁止的)
	     * 版本: version 1.0.0
	     * 时间: 2017年8月3日 下午2:05:19
	     * void
	     */
	    @GetMapping(value="/403")
	    public ModelAndView prohibit(ModelAndView mv,HttpServletRequest request){
	    	try {
	    		ManageAdmin admin=AdminShiroUtil.getUserInfo();
	    		String requestAdminName="匿名";
	    		if(admin!=null){
	    			requestAdminName=admin.getLoginname();
	    		}
				log.info("访问人:【"+requestAdminName+"】IP【"+BaseUtil.getLoginIP(request).toString()+"】访问资源对象错误代码403");
				mv.addObject("", "");
				mv.setViewName("/base/403");
			} catch (Exception e) {
				log.error("打开【403】页面报错",e);
				e.printStackTrace();
			}
	    	
	    	return mv;
	    }
}