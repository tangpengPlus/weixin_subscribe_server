package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.AccAdviserBase;

public interface AccAdviserBaseMapper extends BaseMapper<AccAdviserBase> {
    /**
     *  动态字段,写入数据库记录,acc_adviser_base
     *
     * @param record
     */
    int insertSelective(AccAdviserBase record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,acc_adviser_base
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AccAdviserBase record);
}