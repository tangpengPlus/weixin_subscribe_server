package com.gency.subscribe.service.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.model.system.ManageRole;
import com.gency.subscribe.service.base.GenericService;

public interface RoleService extends GenericService<ManageRole>{
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 查询当前回话管理员的角色信息
	 * 版本: version 1.0.0
	 * 时间: 2017年7月24日 上午10:45:13
	 * @param session
	 * @return
	 * @throws Exception
	 * List<ManageRole>
	 */
	List<ManageRole> selectRoleByAdminId(HttpSession session)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前登录管理员的菜单信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 下午4:18:09
	 * @return
	 * @throws Exception
	 * List<ManageMenu>
	 */
	List<ManageMenu> selectCurrYLoginAdminMenu(Integer adminId)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取当前登录管理员的权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月29日 下午4:18:26
	 * @return
	 * @throws Exception
	 * List<ManageAuthority>
	 */
	List<ManageAuthority> selectCurryLoginAdminAuthor(Integer adminId)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 增加系统权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月31日 下午3:16:06
	 * @param manageRole
	 * @throws Exception
	 * void
	 */
	String addSystemRole(ManageRole manageRole)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 修改系统权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月31日 下午3:16:40
	 * @param manageRole
	 * @throws Exception
	 * void
	 */
	String updateSystemRole(ManageRole manageRole)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 根据角色Id查询角色的详细信息
	 * 版本: version 1.0.0
	 * 时间: 2017年8月31日 下午3:36:28
	 * @param roleId
	 * @return
	 * @throws Exception
	 * ManageRole
	 */
	ManageRole selectManageRoleInfoById(Integer roleId)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 删除系统角色
	 * 版本: version 1.0.0
	 * 时间: 2017年8月31日 下午5:28:25
	 * @param roleId
	 * @throws Exception
	 * void
	 */
	void deleteSystemRole(Integer roleId)throws Exception;

}
