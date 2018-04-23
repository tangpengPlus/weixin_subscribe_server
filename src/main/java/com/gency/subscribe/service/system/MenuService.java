package com.gency.subscribe.service.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.service.base.GenericService;

/**
 * 
 * 作者:唐鹏
 * 描述: 系统菜单接口
 * 版本: version 1.0.0
 * 时间: 2017年7月17日 下午1:42:30
 */
public interface MenuService extends GenericService<ManageMenu>{
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 查询菜单
	 * 版本: version 1.0.0
	 * 时间: 2017年7月17日 下午1:44:24
	 * @param grade 0查询所有菜单 大于0则查询小于或者等于此等级的菜单
	 * @return
	 * @throws Exception
	 * List<ManageMenu>
	 */
	List<ManageMenu> selectMenuList(Integer grade,HttpSession session)throws Exception;
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:获取登录管理员的菜单信息
	 * 版本: version 1.0.0
	 * 时间: 2017年9月1日 上午9:59:52
	 * @return
	 * @throws Exception
	 * List<ManageMenu>
	 */
	List<ManageMenu> selectCurryLoginAdminMenu(Integer adminId)throws Exception;
	
}
