package com.gency.subscribe.service.system;
/**
 * 
 * 作者:唐鹏
 * 描述: Shiro管理服务
 * 版本: version 1.0.0
 * 时间: 2017年7月26日 下午4:10:37
 */
public interface ShiroService {

	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 重新加载角色权限信息到redis缓存中
	 * 版本: version 1.0.0
	 * 时间: 2017年8月3日 下午2:58:18
	 * @param roleId
	 * @throws Exception
	 * void
	 */
	public void againLoadJurisdiction(Integer roleId)throws Exception;
	
	
}
