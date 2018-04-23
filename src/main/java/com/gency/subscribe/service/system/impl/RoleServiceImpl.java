package com.gency.subscribe.service.system.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gency.subscribe.core.constant.message.AlertMessage;
import com.gency.subscribe.core.util.base.GenerateNumber;
import com.gency.subscribe.core.util.system.AdminShiroUtil;
import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.dao.sys.ManageAuthorityMapper;
import com.gency.subscribe.dao.sys.ManageMenuMapper;
import com.gency.subscribe.dao.sys.ManageRoleMapper;
import com.gency.subscribe.model.system.ManageAdmin;
import com.gency.subscribe.model.system.ManageAuthority;
import com.gency.subscribe.model.system.ManageMenu;
import com.gency.subscribe.model.system.ManageRole;
import com.gency.subscribe.service.base.impl.GenericServiceImp;
import com.gency.subscribe.service.system.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImp<ManageRole> implements RoleService{
@Autowired
private ManageRoleMapper manageRoleMapper;
@Autowired
private ManageAuthorityMapper manageAuthorityMapper;
@Autowired
private ManageMenuMapper manageMenuMapper;

	@Override
	public List<ManageRole> selectRoleByAdminId(HttpSession session) throws Exception {
		
		return null;
	}

	@Override
	public BaseMapper<ManageRole> getDao() {
		
		return manageRoleMapper;
	}

	@Override
	public List<ManageMenu> selectCurrYLoginAdminMenu(Integer adminId) throws Exception {
		return manageMenuMapper.selectCurrYLoginAdminMenu2(adminId);
	}

	@Override
	public List<ManageAuthority> selectCurryLoginAdminAuthor(Integer adminId) throws Exception {
		return manageAuthorityMapper.selectManageAuthorityByAdminId2(adminId);
	}

	@Override
	public String addSystemRole(ManageRole manageRole) throws Exception {
		//判断当前角色名称是否已经被使用过
		ManageRole manageRoles=new ManageRole();
		manageRoles.setIsdelete(0);
		manageRoles.setRolename(manageRole.getRolename());
		ManageRole manageRoles2=manageRoleMapper.selectOne(manageRoles);
		if(null!=manageRoles2){
			return AlertMessage.ROLE_NAME_EXIT;
		}
		//保存角色基本信息
		manageRole.setNumber(GenerateNumber.getSysNumber());
		manageRole.setCreateadmin(1);
		manageRole.setCreatetime(new Date());
		manageRoleMapper.save(manageRole);
		//关联角色于菜单信息
		if(manageRole.getChoseMenu()!=null&&manageRole.getChoseMenu().length!=0){
			for (int i = 0; i <manageRole.getChoseMenu().length; i++) {
				manageRoleMapper.addRoleMenuInfo(manageRole.getId(), manageRole.getChoseMenu()[i]);
			}
		}
		if(manageRole.getChoseAuthor()!=null&&manageRole.getChoseAuthor().length!=0){
			for (int i = 0; i < manageRole.getChoseAuthor().length; i++) {
				manageRoleMapper.addRoleAuthorInfo(manageRole.getId(), manageRole.getChoseAuthor()[i]);
			}
		}
		return AlertMessage.OPPTION_SUCCESS;
	}

	@Override
	@CacheEvict(value = "shiro-power", allEntries = true) //移除所有数据  
	public String updateSystemRole(ManageRole manageRole) throws Exception {
		ManageRole manageRoles=new ManageRole();
		manageRoles.setIsdelete(0);
		manageRoles.setRolename(manageRole.getRolename());
		ManageRole manageRoles2=manageRoleMapper.selectOne(manageRoles);
		if(manageRole.getId().intValue()!=manageRoles2.getId().intValue()){
			return AlertMessage.ROLE_NAME_EXIT;
		}
		manageRoleMapper.update(manageRole);
		//删除角色关联菜单信息
		manageRoleMapper.deleteRoleMenuInfo(manageRole.getId());
		//从新生成关联菜单
		if(manageRole.getChoseMenu()!=null&&manageRole.getChoseMenu().length!=0){
			for (int i = 0; i <manageRole.getChoseMenu().length; i++) {
				manageRoleMapper.addRoleMenuInfo(manageRole.getId(), manageRole.getChoseMenu()[i]);
			}
		}
		//删除角色关联权限信息
		manageRoleMapper.deleteRoleAuthorInfo(manageRole.getId());
		//从新生成角色与权限的关联
		if(manageRole.getChoseAuthor()!=null&&manageRole.getChoseAuthor().length!=0){
			for (int i = 0; i < manageRole.getChoseAuthor().length; i++) {
				manageRoleMapper.addRoleAuthorInfo(manageRole.getId(), manageRole.getChoseAuthor()[i]);
			}
		}
		return AlertMessage.OPPTION_SUCCESS;
	}

	@Override
	public ManageRole selectManageRoleInfoById(Integer roleId) throws Exception {
		ManageRole manageRole=manageRoleMapper.selectById(roleId);
		//获取当前角色的菜单信息
		ManageAdmin admin=AdminShiroUtil.getUserInfo();
		List<ManageMenu> menulist=manageMenuMapper.selectCurrYLoginAdminMenu(roleId);
		//获取当前角色的权限信息
		List<ManageAuthority> authorlist=manageAuthorityMapper.selectManageAuthorityByAdminId(roleId);
		List<Integer> menulists=new ArrayList<>();
		List<Integer> authorlists=new ArrayList<>();
		if(null!=menulist){
			for (int i = 0; i < menulist.size(); i++) {
				menulists.add(menulist.get(i).getId());
			}
		}
		if(null!=authorlist){
			for (int i = 0; i <authorlist.size(); i++) {
				authorlists.add(authorlist.get(i).getId());
			}
		}
		manageRole.setChoseAuthor(authorlists.toArray(new Integer [authorlists.size()]));
		manageRole.setChoseMenu(menulists.toArray(new Integer[menulists.size()]));
		return manageRole;
	}

	@Override
	public void deleteSystemRole(Integer roleId) throws Exception {
		manageRoleMapper.delById(roleId);
		manageRoleMapper.deleteRoleAuthorInfo(roleId);
		manageRoleMapper.deleteRoleMenuInfo(roleId);
	}

}
