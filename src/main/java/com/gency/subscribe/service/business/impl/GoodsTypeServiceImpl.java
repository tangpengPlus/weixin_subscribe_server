package com.gency.subscribe.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.business.GoodsTypeMapper;
import com.gency.subscribe.model.business.GoodsType;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.business.GoodsTypeService;
@Service
public class GoodsTypeServiceImpl extends GenericServiceImp<GoodsType> implements GoodsTypeService{


@Autowired
private GoodsTypeMapper goodsTypeMapper;
	
	@Override
	public BaseMapper<GoodsType> getDao() {
		
		return goodsTypeMapper;
	}

}
