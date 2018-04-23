package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.SerUserBase;

public interface SerUserBaseMapper extends BaseMapper<SerUserBase> {
    /**
     *  动态字段,写入数据库记录,ser_user_base
     *
     * @param record
     */
    int insertSelective(SerUserBase record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,ser_user_base
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SerUserBase record);
}