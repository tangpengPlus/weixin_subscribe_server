package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.SerUserBaseMapper;
import com.gency.subscribe.model.business.SerUserBase;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.SerUserBaseService;
@Service
public class SerUserBaseServiceImpl extends GenericServiceImp<SerUserBase> implements SerUserBaseService{

	@Autowired
	private SerUserBaseMapper serUserBaseMapper;
	
	@Override
	public BaseMapper<SerUserBase> getDao() {
		return serUserBaseMapper;
	}

}
