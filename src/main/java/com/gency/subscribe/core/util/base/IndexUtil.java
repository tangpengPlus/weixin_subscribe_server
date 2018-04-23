package com.gency.subscribe.core.util.base;

import java.util.List;
import com.gency.subscribe.model.system.ManageMenu;

/**
 * 
 * 作者:唐鹏
 * 描述:获取系统管理员的首页地址
 * 版本: version 1.0.0
 * 时间: 2017年9月1日 上午9:42:39
 */
public class IndexUtil {

	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 获取首页地址
	 * 版本: version 1.0.0
	 * 时间: 2017年9月1日 上午9:43:54
	 * @param list
	 * @return
	 * @throws Exception
	 * String
	 */
	public static String getSystemIndexUrl(List<ManageMenu> list)throws Exception{
		String url="";
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				//判断
				ManageMenu menu=list.get(i);
				if(menu.getGrade().intValue()==1){
					if(menu.getType().intValue()==1 && menu.getAutoattach().intValue()==1){
						url = menu.getUrl();
						break;
					}else{
						for(ManageMenu menu2:list){
							if(menu2.getGrade().intValue()==2 && menu2.getType().intValue()==1 && menu2.getSuperior().intValue()==menu.getId().intValue()){
								url = menu2.getUrl();
								break;
							}
						}
						
					}
				}
				
			}
		}	
		return url;		
	}
}
