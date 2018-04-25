package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.SerUserCallMapper;
import com.gency.subscribe.model.business.SerUserCall;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.SerUserCallService;
@Service
public class SerUserCallServiceImpl extends GenericServiceImp<SerUserCall> implements SerUserCallService{

	@Autowired
	private SerUserCallMapper serUserCallMapper;
	@Override
	public BaseMapper<SerUserCall> getDao() {
		// TODO Auto-generated method stub
		return serUserCallMapper;
	}

}
