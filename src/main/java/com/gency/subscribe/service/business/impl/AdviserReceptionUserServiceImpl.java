package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.AdviserReceptionUserMapper;
import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.model.business.AdviserReceptionUser;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.AdviserReceptionUserService;
@Service
public class AdviserReceptionUserServiceImpl extends GenericServiceImp<AdviserReceptionUser> implements AdviserReceptionUserService{

	@Autowired
	private AdviserReceptionUserMapper adviserReceptionUserMapper;
	
	@Override
	public BaseMapper<AdviserReceptionUser> getDao() {
		
		return adviserReceptionUserMapper;
	}

	@Override
	public AccAdviserBase getAccAdviserBaseForUser() {
		
		return adviserReceptionUserMapper.getAdviserForUser();
	}

}
