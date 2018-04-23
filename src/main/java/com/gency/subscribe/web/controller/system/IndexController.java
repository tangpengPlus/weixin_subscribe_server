package com.gency.subscribe.web.controller.system;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * 作者:唐鹏
 * 描述: 系统首页控制
 * 版本: version 1.0.0
 * 时间: 2017年9月1日 上午9:36:50
 */
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.IndexUtil;
import com.gency.subscribe.core.util.system.AdminShiroUtil;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.service.system.RoleService;
@Controller
@RequestMapping(value="/system/home")
public class IndexController {

  private static final 	Log log=LogFactory.getLog(IndexController.class);
  @Autowired
  private RoleService roleService;
  /**
   * 
   * 作者:唐鹏
   * 描述:获取当前系统的首页
   * 版本: version 1.0.0
   * 时间: 2017年9月1日 上午9:39:33
   * @param mv
   * @return
   * ModelAndView
   */
  @GetMapping(value="/index")
  public ModelAndView index(ModelAndView mv){
	  log.info("打开系统首页");
	  try {
		  //获取当前登录管理员的所有信息

		  List<ManageMenu> list=roleService.selectCurrYLoginAdminMenu(AdminShiroUtil.getUserInfo().getId());
		  String url=IndexUtil.getSystemIndexUrl(list);
		  //获取当前登录管理员的首页地址
			mv.setViewName("redirect:"+url);
	  } catch (Exception e) {
		  log.error("打开系统首页错误", e);
		e.printStackTrace();
	}
	  return mv;
  }
  
}
