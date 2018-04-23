package com.gency.subscribe.service.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.base.GenericService;
/**
 * 
 * 作者:唐鹏
 * 描述: 系统权限信息扩展
 * 版本: version 1.0.0
 * 时间: 2017年7月24日 上午10:46:39
 */
public interface AuthorityService extends GenericService<ManageAuthority>{
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 查询当前登录管理员的权限信息
	 * 版本: version 1.0.0
	 * 时间: 2017年7月24日 上午10:47:52
	 * @param session 当前会话
	 * @param pb 分页数据封装
	 * @return
	 * @throws Exception
	 * List<ManageAuthority> 权限列表集合
	 */
	List<ManageAuthority> selectManageAuthorityByAdminId(HttpSession session,PageBean pb,Integer adminId)throws Exception;


    /**
     * 
     * 作者:唐鹏
     * 描述: 获取当前登录的管理员的所有权限信息
     * 版本: version 1.0.0
     * 时间: 2017年8月29日 上午10:55:15
     * @return
     * @throws Exception
     * List<ManageAuthority>
     */
    List<ManageAuthority> selectCurryLoginAdminManageAuthority(Integer adminId)throws Exception;
}
