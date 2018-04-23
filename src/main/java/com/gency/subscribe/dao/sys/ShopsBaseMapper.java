package com.gency.subscribe.dao.sys;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.ShopsBase;

public interface ShopsBaseMapper extends BaseMapper<ShopsBase> {
    /**
     *  动态字段,写入数据库记录,ec_shops_base
     *
     * @param record
     */
    int insertSelective(ShopsBase record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,ec_shops_base
     *
     * @param record
     */
    int updateByPrimaryKeySelective(ShopsBase record);

    /**
     * ,ec_shops_base
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(ShopsBase record);
}