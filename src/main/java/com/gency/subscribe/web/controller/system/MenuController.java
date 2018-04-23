package com.gency.subscribe.web.controller.system;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.util.base.GenerateNumber;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.system.MenuService;

/**
 * 
 * 作者:唐鹏
 * 描述: 系统菜单控制
 * 版本: version 1.0.0
 * 时间: 2017年8月22日 上午10:56:02
 */
@Controller
@RequestMapping(value="/system/menu")
public class MenuController {

	private static final Log log=LogFactory.getLog(MenuController.class);
	@Autowired
	private MenuService menuService;
	@Value("${spring.explorationModel}")
	private boolean explorationModel;
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取系统菜单列表
	 * 版本: version 1.0.0
	 * 时间: 2017年8月22日 上午10:58:49
	 * @param pb
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/list")
	public ModelAndView list(PageBean pb,ModelAndView mv){
		log.info("查询系统菜单列表"+pb);
		try {
			mv.setViewName("system/menu/menulist");
			ManageMenu manageMenu=new ManageMenu();
			manageMenu.setIsdelete(0);
			mv.addObject("menuList", menuService.selectList(manageMenu));
		} catch (Exception e) {
			log.error("查询系统菜单列表错误", e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开添加系统菜单页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月22日 上午10:58:49
	 * @param pb
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/add")
	public ModelAndView add(ModelAndView mv){
		log.info("打开添加系统菜单页面");
		try {
			mv.setViewName("system/menu/menuinfo");
			ManageMenu manageMenu=new ManageMenu();
			manageMenu.setIsdelete(0);
			List<ManageMenu> menuslist=menuService.selectList(manageMenu);
			mv.addObject("menuslist", menuslist);
			mv.addObject("manageMenu",manageMenu);
		} catch (Exception e) {
			log.error("打开添加系统菜单页面", e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:添加系统菜单信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月22日 下午2:39:26
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/add")
	public ModelAndView add(ModelAndView mv,@Valid ManageMenu menu,BindingResult result,HttpSession session){
		log.info("打开添加系统菜单页面");
		try {
			if(result.hasErrors()){
				ManageMenu manageMenu=new ManageMenu();
				manageMenu.setIsdelete(0);
				List<ManageMenu> menuslist=menuService.selectList(manageMenu);
				mv.addObject("menuslist", menuslist);
				mv.setViewName("system/menu/menuinfo");
				mv.addObject("manageMenu", menu);
			}else{
				menu.setNumber(GenerateNumber.getSysNumber());
				menu.setCreateadmin("1");
				menu.setCreatetime(new Date());
				menuService.save(menu);
				if(explorationModel){
					ManageMenu t=new ManageMenu();
					t.setIsdelete(0);
					session.setAttribute("curryLoginAdminMenu", menuService.selectList(t));	
				}
				mv.setViewName("redirect:list");
			}
			
		} catch (Exception e) {
			log.error("打开添加系统菜单页面", e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开修改菜单信息页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 上午11:34:28
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/update")
	public ModelAndView update(Integer id,ModelAndView mv){
		log.info("打开修改菜单信息页面");
		try {
			ManageMenu manageMenu=new ManageMenu();
			manageMenu.setIsdelete(0);
			List<ManageMenu> menuslist=menuService.selectList(manageMenu);
			mv.addObject("menuslist", menuslist);
			mv.setViewName("system/menu/menuinfo");
			mv.addObject("manageMenu", menuService.selectById(id));
		} catch (Exception e) {
			log.info("打开修改菜单信息页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开修改菜单信息页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 上午11:34:28
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/update")
	public ModelAndView update(ModelAndView mv,@Valid ManageMenu menu,BindingResult result,HttpSession session){
		log.info("修改菜单信息");
		try {
			if(result.hasErrors()){
				ManageMenu manageMenu=new ManageMenu();
				manageMenu.setIsdelete(0);
				List<ManageMenu> menuslist=menuService.selectList(manageMenu);
				mv.addObject("menuslist", menuslist);
				mv.setViewName("system/menu/menuinfo");
				mv.addObject("manageMenu",menu);
			}else{
				menuService.update(menu);
				if(explorationModel){
					ManageMenu t=new ManageMenu();
					t.setIsdelete(0);
					session.setAttribute("curryLoginAdminMenu", menuService.selectList(t));	
				}
				mv.setViewName("redirect:list");
			}
		} catch (Exception e) {
			log.info("打开修改菜单信息页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:删除菜单信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午12:06:03
	 * @param id
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/delete")
	public ModelAndView delete(Integer id,ModelAndView mv,HttpSession session){
		log.info("删除菜单信息");
		try {
			mv.setViewName("redirect:list");
			menuService.delById(id);
			if(explorationModel){
				ManageMenu t=new ManageMenu();
				t.setIsdelete(0);
				session.setAttribute("curryLoginAdminMenu", menuService.selectList(t));	
			}
		} catch (Exception e) {
			log.info("打开修改菜单信息页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
}
