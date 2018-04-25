package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.AccAdviserBaseMapper;
import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.AdviserService;
@Service
public class AdviserServiceImpl extends GenericServiceImp<AccAdviserBase> implements AdviserService{

	@Autowired
	private AccAdviserBaseMapper accAdviserBaseMapper;
	@Override
	public BaseMapper<AccAdviserBase> getDao() {
		return accAdviserBaseMapper;
	}

}
