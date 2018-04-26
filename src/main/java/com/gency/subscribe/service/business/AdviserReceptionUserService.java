package com.gency.subscribe.service.business;

import com.gency.subscribe.model.business.AccAdviserBase;
import com.gency.subscribe.model.business.AdviserReceptionUser;
import com.gency.subscribe.service.base.GenericService;

public interface AdviserReceptionUserService extends GenericService<AdviserReceptionUser>{

	
	AccAdviserBase getAccAdviserBaseForUser();
	
}
