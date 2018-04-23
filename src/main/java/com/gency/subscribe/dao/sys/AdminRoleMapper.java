package com.gency.subscribe.dao.sys;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.AdminRole;

public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    /**
     *  动态字段,写入数据库记录,manage_admin_role
     *
     * @param record
     */
    int insertSelective(AdminRole record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_admin_role
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdminRole record);
}