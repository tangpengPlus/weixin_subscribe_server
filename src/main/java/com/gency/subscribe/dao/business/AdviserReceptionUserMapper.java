package com.gency.subscribe.dao.business;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.model.business.AdviserReceptionUser;

public interface AdviserReceptionUserMapper extends BaseMapper<AdviserReceptionUser> {
    /**
     *  动态字段,写入数据库记录,adviser_reception_user
     *
     * @param record
     */
    int insertSelective(AdviserReceptionUser record);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,adviser_reception_user
     *
     * @param record
     */
    int updateByPrimaryKeySelective(AdviserReceptionUser record);
    
    
    AccAdviserBase getAdviserForUser();
}