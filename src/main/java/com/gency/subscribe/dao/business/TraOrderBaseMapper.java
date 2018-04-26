package com.gency.subscribe.dao.business;

import org.apache.ibatis.annotations.Param;

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
    
    
    TraOrderBase selectUserAd(@Param("user_tel")String user_tel);
    
    void updateOrderState(@Param("user_tel")String user_tel);
}