package com.gency.subscribe.service.business;

import com.gency.subscribe.model.business.TraOrderBase;
import com.gency.subscribe.service.base.GenericService;

public interface OrderService extends GenericService<TraOrderBase>{

	TraOrderBase selectUserAd(String user_tel);
	
	void updateOrderState(String user_tel);
}
