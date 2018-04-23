package com.gency.subscribe.service.system.impl;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gency.subscribe.core.util.base.PageUtil;
import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.sys.ManageAdminMapper;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.system.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class AdminServiceImpl extends GenericServiceImp<ManageAdmin> implements AdminService {
@Autowired
private ManageAdminMapper adminMapper;
	@Override
	public List<ManageAdmin> selectAdmin(PageBean pb, ManageAdmin admin, HttpSession session) throws Exception {
		List<ManageAdmin> list=new ArrayList<ManageAdmin>();
		ManageAdmin sessionAdmin=(ManageAdmin) session.getAttribute("admin");
		admin.setCreateadmin(sessionAdmin.getId());
		 Page<?> page=PageHelper.startPage(pb.getCurPage(), 7);
		list=adminMapper.selectList(admin);
		PageUtil.getPageNumber(pb, page);
		return list;
	}
	@Override
	public BaseMapper<ManageAdmin> getDao() {
		// TODO Auto-generated method stub
		return adminMapper;
	}
	@Override
	public boolean isSuperAdmin(HttpSession session) throws Exception {
		ManageAdmin sessionAdmin=(ManageAdmin) session.getAttribute("admin");
		if(null==sessionAdmin){
			return false;
		}
		ManageAdmin admins=new ManageAdmin();
		admins.setId(sessionAdmin.getId());
		ManageAdmin admin=adminMapper.selectOne(admins);
		if(admin==null){
			return false;
		}
		if(admin.getType()!=null&&admin.getType().intValue()==1){
			return true;
		}
		return false;
	}
	@Override
	public ManageAdmin selectCurryLogAdminInfo(HttpSession session) throws Exception {
		ManageAdmin sessionAdmin=(ManageAdmin) session.getAttribute("admin");
		ManageAdmin admin=new ManageAdmin();
		admin.setId(sessionAdmin.getId());
		return adminMapper.selectOne(admin);
	}
	@Override
	public void addAdminRelationRole(Integer roleId, Integer adminId) throws Exception {
		adminMapper.addAdminRelationRole(adminId, roleId);
		
	}
	@Override
	public void deleteAdminRelationRole(Integer adminId) throws Exception {
		adminMapper.deleteAdminRelationRole(adminId);
		
	}
	@Override
	public Integer[] seletAdminRelationRole(Integer adminId) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.getAdminRoleRelation(adminId);
	}

}
