package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.TraOrderBase;

public interface TraOrderBaseMapper extends BaseMapper<TraOrderBase> {
    /**
     *  动态字段,写入数据库记录,tra_order_base
     *
     * @param record
     */
    int insertSelective(TraOrderBase record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,tra_order_base
     *
     * @param record
     */
    int updateByPrimaryKeySelective(TraOrderBase record);
}