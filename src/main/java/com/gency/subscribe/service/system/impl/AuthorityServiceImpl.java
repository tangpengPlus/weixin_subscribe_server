package com.gency.subscribe.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gency.subscribe.core.constant.base.SystemConstant;
import com.gency.subscribe.core.util.base.PageUtil;
import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.sys.ManageAuthorityMapper;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.system.AdminService;
import com.gency.subscribe.service.system.AuthorityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
@Transactional
public class AuthorityServiceImpl extends GenericServiceImp<ManageAuthority> implements AuthorityService{
@Autowired
private ManageAuthorityMapper manageAuthorityMapper;
@Autowired
private AdminService adminService;
@Value("${spring.explorationModel}")
private boolean explorationModel;
	@Override
	public List<ManageAuthority> selectManageAuthorityByAdminId(HttpSession session,PageBean pb,Integer adminId) throws Exception {
		/*初始化存储权限对象集合*/
		List<ManageAuthority> list=new ArrayList<ManageAuthority>();
		/*获取当前登录管理员的信息*/
		ManageAdmin admin=null;
		if(session!=null){
			admin=(ManageAdmin) session.getAttribute("admin");
		}else{
			if(adminId!=null&&adminId.intValue()!=0){
				admin=adminService.selectById(adminId);
			}
		}
		if(null==admin){
			return list;
		}
		/*判断当前登录管理是否为超级管理员*/
		boolean ok=adminService.isSuperAdmin(session);
		/*分页处理*/
		if(pb!=null){
			Page<?> page=PageHelper.startPage(pb.getCurPage(), SystemConstant.PAGE_SIZE);	
			if(ok){
				ManageAuthority authority=new ManageAuthority();
				authority.setIsdelete(0);
				list=manageAuthorityMapper.selectList(authority);
			}else{
				//list=manageAuthorityMapper.selectManageAuthorityByAdmin(admin.getId());
			}
			PageUtil.getPageNumber(pb, page);
		}else{
			if(ok){
				ManageAuthority authority=new ManageAuthority();
				authority.setIsdelete(0);
				list=manageAuthorityMapper.selectList(authority);
			}else{
				//list=manageAuthorityMapper.selectManageAuthorityByAdmin(admin.getId());
			}
		}
		return list;
	}

	@Override
	public BaseMapper<ManageAuthority> getDao() {
	
		return manageAuthorityMapper;
	}

	@Override
	@Cacheable(value="shiro-power",key="#adminId")
	public List<ManageAuthority> selectCurryLoginAdminManageAuthority(Integer adminId) throws Exception {
		if(explorationModel){
			ManageAuthority authority=new ManageAuthority();
			authority.setIsdelete(0);
		  return manageAuthorityMapper.selectList(authority);
		}
		return manageAuthorityMapper.selectManageAuthorityByAdminId(adminId);
	}
}
