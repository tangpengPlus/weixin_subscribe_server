package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.GoodsType;

public interface GoodsTypeMapper extends BaseMapper<GoodsType> {
    /**
     *  动态字段,写入数据库记录,goods_type
     *
     * @param record
     */
    int insertSelective(GoodsType record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,goods_type
     *
     * @param record
     */
    int updateByPrimaryKeySelective(GoodsType record);
}