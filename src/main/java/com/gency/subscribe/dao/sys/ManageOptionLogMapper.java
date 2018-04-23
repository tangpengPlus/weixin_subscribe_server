package com.gency.subscribe.dao.sys;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ManageOptionLog;

public interface ManageOptionLogMapper extends BaseMapper<ManageOptionLog> {
    /**
     *  动态字段,写入数据库记录,manage_option_log
     *
     * @param record
     */
    int insertSelective(ManageOptionLog record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,manage_option_log
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ManageOptionLog record);
}