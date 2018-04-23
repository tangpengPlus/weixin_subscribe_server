package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.Goods;

public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     *  动态字段,写入数据库记录,goods
     *
     * @param record
     */
    int insertSelective(Goods record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,goods
     *
     * @param record
     */
    int updateByPrimaryKeySelective(Goods record);
}