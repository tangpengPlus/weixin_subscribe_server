package com.gency.subscribe.dao.sys;



import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ManageAuthority;


public interface ManageAuthorityMapper extends BaseMapper<ManageAuthority> {
    /**
     *  动态字段,写入数据库记录,manage_authority
     *
     * @param record
     */
    int insertSelective(ManageAuthority record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_authority
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ManageAuthority record);
    
    /**
     * 
     * 作者:唐鹏
     * 描述: 根据管理员Id查询管理员的所有权限信息
     * 版本: version 1.0.0
     * 时间: 2017年8月29日 上午10:58:14
     * @param adminId
     * @return
     * List<ManageAuthority>
     */
    List<ManageAuthority> selectManageAuthorityByAdminId(@Param("roleId")Integer roleId);
    
    
    List<ManageAuthority> selectManageAuthorityByAdminId2(@Param("adminId")Integer adminId);
}