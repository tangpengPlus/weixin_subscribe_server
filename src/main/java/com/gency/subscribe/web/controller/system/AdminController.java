package com.gency.subscribe.web.controller.system;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gency.subscribe.core.constant.message.AlertMessage;
import com.gency.subscribe.core.util.base.GenerateNumber;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.ManageRole;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.system.AdminService;
import com.gency.subscribe.service.system.EncryptionService;
import com.gency.subscribe.service.system.RoleService;

/**
 * 
 * 作者:唐鹏
 * 描述: 管理员控制
 * 版本: version 1.0.0
 * 时间: 2017年8月24日 下午2:37:17
 */
@Controller
@RequestMapping(value="/system/admin")
public class AdminController {
	private static final Log log =LogFactory.getLog(AdminController.class);
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	EncryptionService encryptionService;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:获取管理员列表
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午2:45:22
	 * @param pb
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/list")
	public ModelAndView list(PageBean pb,ModelAndView mv,HttpSession session){
		log.info("获取管理员列表");
		try {
				 mv.addObject("adminlist", adminService.selectListPage(pb, null));
				 mv.addObject("pagebean",pb);
				 mv.setViewName("system/admin/adminlist");
		} catch (Exception e) {
			log.error("获取管理员列表信息错误",e);
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开增加管理员页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午3:28:59
	 * @param mv
	 * @param session
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/add")
	public ModelAndView add(ModelAndView mv,HttpSession session){
		log.info("打开增加管理员页面");
		try {
			mv.setViewName("system/admin/admininfo");
			mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
			mv.addObject("manageAdmin", new ManageAdmin());
			ManageRole role=new ManageRole();
			role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
			mv.addObject("rolelist", roleService.selectList(role));
		} catch (Exception e) {
			log.error("打开增加管理员页面错误", e);
			e.printStackTrace();
		}
		return mv;
	}
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:增加管理员信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午3:37:21
	 * @param mv
	 * @param session
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/add")
	public ModelAndView add(@Valid ManageAdmin manageAdmin,BindingResult result,ModelAndView mv,HttpSession session){
		log.info("增加管理员信息开始");
		try {
		if(result.hasErrors()){
			mv.setViewName("system/admin/admininfo");
			mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
			mv.addObject("manageAdmin", manageAdmin);
			ManageRole role=new ManageRole();
			role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
			mv.addObject("rolelist", roleService.selectList(role));
			return mv;
		}
		if(manageAdmin.getRoleIds()==null||manageAdmin.getRoleIds().length==0){
			mv.setViewName("system/admin/admininfo");
			mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
			mv.addObject("manageAdmin", manageAdmin);
			mv.addObject("pageShowMessage", AlertMessage.NOT_CHOSE_ROLE);
			ManageRole role=new ManageRole();
			role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
			mv.addObject("rolelist", roleService.selectList(role));
			return mv;
		}
			ManageAdmin admins=new ManageAdmin();
			admins.setLoginname(manageAdmin.getLoginname());
			ManageAdmin manageAdmin2=adminService.selectOne(admins);
			if(null!=manageAdmin2){
				mv.setViewName("system/admin/admininfo");
				mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
				mv.addObject("manageAdmin", manageAdmin);
				mv.addObject("pageShowMessage", AlertMessage.ADMIN_NAME_EXIT);
				ManageRole role=new ManageRole();
				role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
				mv.addObject("rolelist", roleService.selectList(role));
				return mv;
			}
			ManageAdmin admin=adminService.selectCurryLogAdminInfo(session);
			manageAdmin.setNumber(GenerateNumber.getSysNumber());
			manageAdmin.setCreatetime(new Date());
			manageAdmin.setCreateadmin(admin.getId());
			String salt=encryptionService.getSalt();
			manageAdmin.setSalt(salt);
			manageAdmin.setPassword(encryptionService.md5Password(manageAdmin.getLoginname(), manageAdmin.getUnencryptedpassword(), salt, 2));
			adminService.save(manageAdmin);
			//增加管理员和角色信息的关联
			for (int i = 0; i < manageAdmin.getRoleIds().length; i++) {
				adminService.addAdminRelationRole(manageAdmin.getRoleIds()[i], manageAdmin.getId());
			}
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			log.error("增加管理员信息错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 打开修改管理员的页面
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午3:45:40
	 * @param mv
	 * @param id
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/update")
	public ModelAndView update(ModelAndView mv,Integer id,HttpSession session){
		log.info("打开修改管理员的页面");
		try {
			ManageAdmin admin=adminService.selectById(id);
			admin.setRoleIds(adminService.seletAdminRelationRole(id));
			mv.addObject("manageAdmin",admin);
			mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
			ManageRole role=new ManageRole();
			role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
			mv.addObject("rolelist", roleService.selectList(role));
			mv.setViewName("system/admin/admininfo");
		} catch (Exception e) {
			log.error("打开修改管理员的页面错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 修改管理员信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午3:50:04
	 * @param manageAdmin
	 * @param result
	 * @param mv
	 * @param session
	 * @return
	 * ModelAndView
	 */
	@PostMapping(value="/update")
	public ModelAndView update(@Valid ManageAdmin manageAdmin,BindingResult result,ModelAndView mv,HttpSession session){
		log.info("修改管理员信息开始");
		try {
			if(result.hasErrors()){
				mv.setViewName("system/admin/admininfo");
				mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
				mv.addObject("manageAdmin", manageAdmin);
				ManageRole role=new ManageRole();
				role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
				mv.addObject("rolelist", roleService.selectList(role));
				return mv;
			}
			if(manageAdmin.getRoleIds()==null||manageAdmin.getRoleIds().length==0){
				mv.setViewName("system/admin/admininfo");
				mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
				mv.addObject("manageAdmin", manageAdmin);
				mv.addObject("pageShowMessage", AlertMessage.NOT_CHOSE_ROLE);
				ManageRole role=new ManageRole();
				role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
				mv.addObject("rolelist", roleService.selectList(role));
				return mv;
			}
				ManageAdmin admins=new ManageAdmin();
				admins.setLoginname(manageAdmin.getLoginname());
				ManageAdmin manageAdmin2=adminService.selectOne(admins);
				if(manageAdmin2.getId()!=manageAdmin.getId()){
					mv.setViewName("system/admin/admininfo");
					mv.addObject("isSuperAdmin", adminService.isSuperAdmin(session));
					mv.addObject("manageAdmin", manageAdmin);
					mv.addObject("pageShowMessage", AlertMessage.ADMIN_NAME_EXIT);
					ManageRole role=new ManageRole();
					role.setCreateadmin(adminService.selectCurryLogAdminInfo(session).getId());
					mv.addObject("rolelist", roleService.selectList(role));
					return mv;
				}
				//更新密码
				String salt=encryptionService.getSalt();
				manageAdmin.setSalt(salt);
				manageAdmin.setPassword(encryptionService.md5Password(manageAdmin.getLoginname(), manageAdmin.getUnencryptedpassword(), salt, 2));
				adminService.update(manageAdmin);
				//更新账号与管理员之间的关联
				adminService.deleteAdminRelationRole(manageAdmin.getId());
				//增加管理员和角色信息的关联
				for (int i = 0; i < manageAdmin.getRoleIds().length; i++) {
					adminService.addAdminRelationRole(manageAdmin.getRoleIds()[i], manageAdmin.getId());
				}
				mv.setViewName("redirect:list");
		} catch (Exception e) {
			log.error("修改管理员错误",e);
			e.printStackTrace();
		}
		return mv;
	}
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 删除管理员信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月24日 下午4:05:43
	 * @param id
	 * @param mv
	 * @return
	 * ModelAndView
	 */
	@GetMapping(value="/delete")
	public ModelAndView delete(Integer id,ModelAndView mv){
		log.info("删除管理员");
		try {
			adminService.delById(id);
			//更新账号与管理员之间的关联
			adminService.deleteAdminRelationRole(id);
			mv.setViewName("redirect:list");
		} catch (Exception e) {
			log.error("删除管理员错误",e);
		}
		return mv;
	}
	
}
