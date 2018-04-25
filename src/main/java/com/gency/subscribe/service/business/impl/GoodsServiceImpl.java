package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.GoodsMapper;
import com.gency.subscribe.model.business.Goods;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.GoodsService;
@Service
public class GoodsServiceImpl extends GenericServiceImp<Goods> implements GoodsService{

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public BaseMapper<Goods> getDao() {
		// TODO Auto-generated method stub
		return goodsMapper;
	}

}
