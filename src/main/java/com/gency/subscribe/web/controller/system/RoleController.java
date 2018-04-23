package com.gency.subscribe.web.controller.system;

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

import com.gency.subscribe.core.constant.message.AlertMessage;
import com.gency.subscribe.core.util.system.AdminShiroUtil;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.model.system.ManageRole;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.system.AdminService;
import com.gency.subscribe.service.system.AuthorityService;
import com.gency.subscribe.service.system.MenuService;
import com.gency.subscribe.service.system.RoleService;

/**
 * 
 * 作者:唐鹏
 * 描述: 系统角色控制
 * 版本: version 1.0.0
 * 时间: 2017年8月24日 上午11:56:01
 */
@Controller
@RequestMapping(value="/system/role")
public class RoleController {

	private static final Log log =LogFactory.getLog(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private AuthorityService authorityService;
	@Value("${spring.explorationModel}")
	private boolean explorationModel;
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开系统角色列表
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:55:01
	 * void
	 */
	@GetMapping(value="/list")
	public ModelAndView list(PageBean pb,ModelAndView mv){
		log.info("分页查询系统角色列表信息");
		try {
			ManageRole role=new ManageRole();
			role.setIsdelete(0);
			mv.addObject("rolelist", roleService.selectListPage(pb,role));
			mv.addObject("pagebean", pb);
			mv.setViewName("system/role/rolelist");
		} catch (Exception e) {
			log.error("分页查询系统角色列表信息报错",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:打开增加系统角色信息页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:59:15
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/add")
	public ModelAndView add(ModelAndView mv,HttpSession session){
		log.info("打开增加系统角色信息页面");
		try {
			mv.addObject("manageRole", new ManageRole());
			getCurrYloginAdminMenuAndAuthorinfo(mv,session);
			mv.setViewName("system/role/roleinfo");
		} catch (Exception e) {
			log.error("打开增加系统角色信息页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:增加系统角色信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午2:59:15
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/add")
	public ModelAndView add(ModelAndView mv,@Valid ManageRole manageRole,BindingResult result,HttpSession session){
		log.info("增加系统角色信息");
		try {
			if(result.hasErrors()){
				mv.addObject("manageRole",manageRole);
				getCurrYloginAdminMenuAndAuthorinfo(mv,session);
				mv.setViewName("system/role/roleinfo");
			}else{
				String message=roleService.addSystemRole(manageRole);
				if(!message.equals(AlertMessage.OPPTION_SUCCESS)){
					mv.addObject("pageShowMessage",message);
					mv.setViewName("system/role/roleinfo");
					getCurrYloginAdminMenuAndAuthorinfo(mv,session);
				}else{
					mv.setViewName("redirect:list");
				}
			}
			
		} catch (Exception e) {
			log.error("增加系统角色信息错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:打开修改系统角色页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午3:06:59
	 * @param mv
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/update")
	public ModelAndView add(ModelAndView mv,Integer id,HttpSession session){
		log.info("打开修改系统角色页面");
		try {
			mv.setViewName("system/role/roleinfo");
			getCurrYloginAdminMenuAndAuthorinfo(mv,session);
			mv.addObject("manageRole",roleService.selectManageRoleInfoById(id));
		} catch (Exception e) {
			log.error("打开修改系统角色页面错误",e);
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
	public ModelAndView update(ModelAndView mv,@Valid ManageRole manageRole,BindingResult result,HttpSession session){
		log.info("修改系统角色信息");
		try {
			if(result.hasErrors()){
				mv.addObject("manageAuthority",manageRole);
				getCurrYloginAdminMenuAndAuthorinfo(mv,session);
				mv.setViewName("system/role/roleinfo");
			}else{
				String message=roleService.updateSystemRole(manageRole);
				if(!message.equals(AlertMessage.OPPTION_SUCCESS)){
					mv.addObject("pageShowMessage",message);
					getCurrYloginAdminMenuAndAuthorinfo(mv,session);
					mv.setViewName("system/role/roleinfo");
				}else{
					if(explorationModel){
						ManageMenu t=new ManageMenu();
						t.setIsdelete(0);
						session.setAttribute("curryLoginAdminMenu", menuService.selectList(t));	
					}
					mv.setViewName("redirect:list");
				}
			}
		} catch (Exception e) {
			log.error("修改系统角色信息错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:删除系统角色
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 下午3:09:00
	 * @param mv
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/delete")
	public ModelAndView delete(ModelAndView mv,Integer id){
		log.info("删除系统角色");
		try {
			roleService.deleteSystemRole(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			log.error("删除系统角色错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前登录管理员的菜单和权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 下午4:43:00
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	private ModelAndView getCurrYloginAdminMenuAndAuthorinfo(ModelAndView mv,HttpSession session){
		log.info("获取当前登录管理员的菜单和权限信息");
		try {
			if(adminService.isSuperAdmin(session)){
				//菜单信息
				ManageMenu manageMenu=new ManageMenu();
				manageMenu.setIsdelete(0);
				mv.addObject("menuList",menuService.selectList(manageMenu));
				mv.addObject("isSuperAdmin", true);
				//权限信息
				ManageAuthority authority=new ManageAuthority();
				authority.setIsdelete(0);
				mv.addObject("authorList", authorityService.selectList(authority));
			
			}else{
				mv.addObject("menuList", roleService.selectCurrYLoginAdminMenu(AdminShiroUtil.getUserInfo().getId()));
				mv.addObject("authorList", roleService.selectCurryLoginAdminAuthor(AdminShiroUtil.getUserInfo().getId()));
			}
		} catch (Exception e) {
			log.error("获取当前登录管理员的菜单和权限信息错误", e);
			e.printStackTrace();
		}
		return mv;
	}
}
