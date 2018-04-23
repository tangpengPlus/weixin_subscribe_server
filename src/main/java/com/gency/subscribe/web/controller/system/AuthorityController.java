package com.gency.subscribe.web.controller.system;
import java.util.Date;

import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.GenerateNumber;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.system.AuthorityService;
import com.gency.subscribe.service.system.MenuService;
/**
 * 
 * 作者:唐鹏
 * 描述: 系统权限控制
 * 版本: version 1.0.0
 * 时间: 2017年8月23日 下午2:52:41
 */
@Controller
@RequestMapping(value="/system/authority")
public class AuthorityController {

	private static final Log log=LogFactory.getLog(AuthorityController.class);
	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private MenuService menuService;
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开系统权限列表
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:55:01
	 * void
	 */
	@GetMapping(value="/list")
	public ModelAndView list(PageBean pb,ModelAndView mv){
		log.info("分页查询系统权限信息");
		try {
			ManageAuthority authority=new ManageAuthority();
			authority.setIsdelete(0);
			mv.addObject("authorityList", authorityService.selectListPage(pb, authority));
			mv.addObject("pagebean", pb);
			mv.setViewName("system/authority/authoritylist");
		} catch (Exception e) {
			log.error("分页查询系统权限信息报错",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:打开增加系统权限信息页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:59:15
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/add")
	public ModelAndView add(ModelAndView mv){
		log.info("打开增加系统权限信息页面");
		try {
			ManageMenu manageMenu=new ManageMenu();
			manageMenu.setIsdelete(0);
			mv.addObject("menulist", menuService.selectList(manageMenu));
			mv.addObject("manageAuthority", new ManageAuthority());
			mv.setViewName("system/authority/authorityinfo");
		} catch (Exception e) {
			log.error("打开增加系统权限信息页面错",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:增加系统权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:59:15
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/add")
	@CacheEvict(value = "shiro-power", allEntries = true) //移除所有数据  
	public ModelAndView add(ModelAndView mv,@Valid ManageAuthority authority,BindingResult result){
		log.info("增加系统权限信息");
		try {
			if(result.hasErrors()){
				ManageMenu manageMenu=new ManageMenu();
				manageMenu.setIsdelete(0);
				mv.addObject("menulist", menuService.selectList(manageMenu));
				mv.addObject("manageAuthority",authority);
				mv.setViewName("system/authority/authorityinfo");
			}else{
				authority.setNumber(GenerateNumber.getSysNumber());
				authority.setCreateadmin(1);
				authority.setCreatetime(new Date());
				authorityService.save(authority);
				mv.setViewName("redirect:list");
			}
			
		} catch (Exception e) {
			log.error("增加系统权限信息错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:打开修改系统权限页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午3:06:59
	 * @param mv
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/update")
	public ModelAndView add(ModelAndView mv,Integer id){
		log.info("打开增加系统权限信息页面");
		try {
			mv.setViewName("system/authority/authorityinfo");
			ManageMenu manageMenu=new ManageMenu();
			manageMenu.setIsdelete(0);
			mv.addObject("menulist", menuService.selectList(manageMenu));
			mv.addObject("manageAuthority",authorityService.selectById(id));
		} catch (Exception e) {
			log.error("打开修改系统权限页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:修改系统权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午3:08:04
	 * @param mv
	 * @param authority
	 * @param result
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/update")
	@CacheEvict(value = "shiro-power", allEntries = true) //移除所有数据  
	public ModelAndView update(ModelAndView mv,@Valid ManageAuthority authority,BindingResult result){
		log.info("修改系统权限信息");
		try {
			if(result.hasErrors()){
				ManageMenu manageMenu=new ManageMenu();
				manageMenu.setIsdelete(0);
				mv.addObject("menulist", menuService.selectList(manageMenu));
				mv.addObject("manageAuthority",authority);
				mv.setViewName("system/authority/authorityinfo");
			}else{
				authorityService.update(authority);
				mv.setViewName("redirect:list");
			}
		} catch (Exception e) {
			log.error("修改系统权限信息错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:删除系统权限
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午3:09:00
	 * @param mv
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/delete")
	public ModelAndView delete(ModelAndView mv,Integer id){
		log.info("删除系统权限");
		try {
			authorityService.delById(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			log.error("删除系统权限错误",e);
			e.printStackTrace();
		}
		return mv;
	}
}
