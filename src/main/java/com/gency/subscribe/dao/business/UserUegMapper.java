package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.UserUeg;

public interface UserUegMapper extends BaseMapper<UserUeg> {
    /**
     *  动态字段,写入数据库记录,reg_user_reg
     *
     * @param record
     */
    int insertSelective(UserUeg record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,reg_user_reg
     *
     * @param record
     */
    int updateByPrimaryKeySelective(UserUeg record);
}