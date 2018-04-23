package com.gency.subscribe.service.system.impl;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.service.system.AdminService;
import com.gency.subscribe.service.system.ShiroService;


@Service
public class ShiroServiceImpl implements ShiroService{
@Autowired
private AdminService adminService; 
	@Override
	public void againLoadJurisdiction(Integer roleId) throws Exception {
		/*查询当前角色下所有的管理员信息*/
		List<ManageAdmin> admin=adminService.selectList(new ManageAdmin());
		RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();  
		 
	}

}
