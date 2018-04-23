package com.gency.subscribe.dao.sys;

import org.apache.ibatis.annotations.Param;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ManageAdmin;


public interface ManageAdminMapper extends BaseMapper<ManageAdmin> {
    /**
     *  动态字段,写入数据库记录,manage_admin
     *
     * @param record
     */
    int insertSelective(ManageAdmin record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_admin
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ManageAdmin record);
    
    void addAdminRelationRole(@Param("adminId")Integer adminId,@Param("roleId")Integer roleId);
    
    void deleteAdminRelationRole(@Param("adminId")Integer adminId);
    
    Integer [] getAdminRoleRelation(@Param("adminId")Integer adminId);
}