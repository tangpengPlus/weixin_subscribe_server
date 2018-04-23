package com.gency.subscribe.dao.sys;

import org.apache.ibatis.annotations.Param;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ManageRole;


public interface ManageRoleMapper extends BaseMapper<ManageRole> {
    /**
     *  动态字段,写入数据库记录,manage_role
     *
     * @param record
     */
    int insertSelective(ManageRole record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ManageRole record);
    
    /**
     * 
     * 作者:唐鹏
     * 描述:增加角色与菜单的关联
     * 版本: version 1.0.0
     * 时间: 2017年8月31日 下午3:19:11
     * @param roleId
     * @param menuId
     * void
     */
    void addRoleMenuInfo(@Param("roleId")Integer roleId,@Param("menuId")Integer menuId);
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 增加角色与权限的关联
     * 版本: version 1.0.0
     * 时间: 2017年8月31日 下午3:20:09
     * @param roleId
     * @param aurhorId
     * void
     */
    void addRoleAuthorInfo(@Param("roleId")Integer roleId,@Param("authorId")Integer aurhorId);
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 删除角色与菜单的关联
     * 版本: version 1.0.0
     * 时间: 2017年8月31日 下午3:21:01
     * @param roleId
     * void
     */
    void deleteRoleMenuInfo(@Param("roleId")Integer roleId);
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 删除角色与权限的关联
     * 版本: version 1.0.0
     * 时间: 2017年8月31日 下午3:21:28
     * @param roleId
     * void
     */
    void deleteRoleAuthorInfo(@Param("roleId")Integer roleId);
}