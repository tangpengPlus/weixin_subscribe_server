package com.gency.subscribe.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.sys.ManageOptionLogMapper;
import com.gency.subscribe.model.system.ManageOptionLog;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.system.SystemLogService;
@Service
public class SystemLogServiceImpl extends GenericServiceImp<ManageOptionLog> implements SystemLogService{
@Autowired
private ManageOptionLogMapper manageOptionLogMapper;
	@Override
	public BaseMapper<ManageOptionLog> getDao() {
		return manageOptionLogMapper;
	}

}
