package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.TraOrderBaseMapper;
import com.gency.subscribe.model.business.TraOrderBase;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.OrderService;
@Service
public class OrderServiceImpl extends GenericServiceImp<TraOrderBase> implements OrderService{

	@Autowired
	private TraOrderBaseMapper traOrderBaseMapper;
	@Override
	public BaseMapper<TraOrderBase> getDao() {
		return traOrderBaseMapper;
	}
	@Override
	public TraOrderBase selectUserAd(String user_tel) {
		// TODO Auto-generated method stub
		return traOrderBaseMapper.selectUserAd(user_tel);
	}
	@Override
	public void updateOrderState(String user_tel) {
		traOrderBaseMapper.updateOrderState(user_tel);
		
	}

}
