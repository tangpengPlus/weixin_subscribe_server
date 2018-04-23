package com.gency.subscribe.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ManageMenu;


public interface ManageMenuMapper extends BaseMapper<ManageMenu> {
    /**
     *  动态字段,写入数据库记录,manage_menu
     *
     * @param record
     */
    int insertSelective(ManageMenu record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_menu
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ManageMenu record);
    
    /**
     * 
     * 作者:唐鹏
     * 描述:
     * 版本: version 1.0.0
     * 时间: 2017年8月29日 下午4:24:54
     * @return
     * List<ManageMenu>
     */
    List<ManageMenu> selectCurrYLoginAdminMenu(@Param("roleId")Integer roleId);
    
    List<ManageMenu> selectCurrYLoginAdminMenu2(@Param("adminId")Integer adminId);
}