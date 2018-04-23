package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.SerUserCall;

public interface SerUserCallMapper extends BaseMapper<SerUserCall> {
    /**
     *  动态字段,写入数据库记录,ser_user_call
     *
     * @param record
     */
    int insertSelective(SerUserCall record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,ser_user_call
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SerUserCall record);
}