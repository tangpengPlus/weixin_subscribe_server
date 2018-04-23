package com.gency.subscribe.service.system;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.base.GenericService;

/**
 * 
 * 作者:唐鹏
 * 描述: 【权限管理】-管理员接口
 * 版本: version 1.0.0
 * 时间: 2017年7月14日 下午1:45:31
 */
public interface AdminService extends GenericService<ManageAdmin>{
  /**
   * 
   * 作者:唐鹏
   * 描述: 分页查询管理员列表信息
   * 版本: version 1.0.0
   * 时间: 2017年7月14日 下午1:47:51
   * @param pb:分页封装
   * @param admin:管理员昵称 模糊查询
   * @return
   * @throws Exception
   * List<Admin>
   */
  List<ManageAdmin> selectAdmin(PageBean pb,ManageAdmin admin,HttpSession session)throws Exception;
  
  /**
   * 
   * 作者:唐鹏
   * 描述: 判断当前会话管理员是否为超级管理员
   * 版本: version 1.0.0
   * 时间: 2017年7月24日 上午11:08:42
   * @param session
   * @return
   * @throws Exception
   * boolean
   */
  boolean isSuperAdmin(HttpSession session)throws Exception;
  
  /**
   * 
   * 作者:唐鹏
   * 描述: 获取当前登录管理员信息
   * 版本: version 1.0.0
   * 时间: 2017年8月24日 下午3:08:32
   * @param session
   * @return
   * @throws Exception
   * ManageAdmin
   */
  ManageAdmin selectCurryLogAdminInfo(HttpSession session)throws Exception;
  
  /**
   * 
   * 作者:唐鹏
   * 描述: 增加角色和管理员之间的关联
   * 版本: version 1.0.0
   * 时间: 2017年9月5日 上午10:11:41
   * @param roleId
   * @param adminId
   * @throws Exception
   * void
   */
  void addAdminRelationRole(Integer roleId,Integer adminId)throws Exception;
  
  /**
   * 
   * 作者:唐鹏
   * 描述:删除管理员和角色之间的关联
   * 版本: version 1.0.0
   * 时间: 2017年9月5日 上午10:12:29
   * @param adminId
   * @throws Exception
   * void
   */
  void deleteAdminRelationRole(Integer adminId)throws Exception;
  
  /**
   * 
   * 作者:唐鹏
   * 描述: 获取当前账号的角色信息
   * 版本: version 1.0.0
   * 时间: 2017年9月5日 上午10:22:38
   * @param adminId
   * @return
   * @throws Exception
   * Integer[]
   */
  Integer [] seletAdminRelationRole(Integer adminId)throws Exception;

}
