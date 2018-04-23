package com.gency.subscribe.service.base.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.gency.subscribe.core.constant.base.SystemConstant;
import com.gency.subscribe.core.util.base.PageUtil;
import com.gency.subscribe.dao.base.BaseMapper;
import com.gency.subscribe.model.system.PageBean;
import com.gency.subscribe.service.base.GenericService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
/**
 * 
 * 作者:唐鹏
 * 描述:普通实现类继承于此类 用于封装mybatis生成的增删改查接口
 * 版本: version 1.0.0
 * 时间: 2017年7月17日 下午1:51:57
 * @param <T> 泛型对象
 */
public abstract class GenericServiceImp<T> implements GenericService<T> {
 
	 /**
     * 定义成抽象方法,由子类实现,完成dao的注入
     *
     * @return BaseMapper实现类
     */
    public abstract BaseMapper<T> getDao();
    /**
     * 插入对象
     *
     * @param t 对象
     */
    public int save(T t)throws Exception {
        return getDao().save(t);
    }
    /**
     * 批量插入对象
     * 
     * @param t 对象集合
     */
    public int batchSave(List<T> t)throws Exception{
    	return getDao().batchSave(t);
    }
    
    /**
     * 更新对象
     *
     * @param t 对象
     */
    public int update(T t)throws Exception {
        return getDao().update(t);
    }
     /**
      * 批量更新对象
      * 
      * @param list 对象集合
      */
    public int batchUpdate(List<T> list)throws Exception{
    	return getDao().batchUpdate(list);
    }
    
    
    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    public int delById(Serializable id)throws Exception {
        return getDao().delById(id);
    }

    /**
     * 批量删除操作
     * 
     * @param list 对象
     */
    public int delList(List<T> list)throws Exception{
    	return getDao().delList(list);
    }
    
    /**
     * 批量删除操作
     * ids:数据Id对象集合
     */
    public int delArray(int[] ids)throws Exception{
    	return getDao().delArray(ids);	
    }
    /**
     * 根据条件进行删除
     * t：条件对象
     */
    public int delByCondition(T t)throws Exception{
    	return getDao().delByCondition(t);
    }
    
    /**
     * 根据主键进行删除
     */
    public int delById(Integer id)throws Exception{
    	return getDao().delById(id);
    }
    
    /**
     * 统计查询
     * 
     * t:条件对象
     */
    public int count(T t)throws Exception{
    	return getDao().count(t);
    }
    
    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return
     */
    public T selectById(Serializable id)throws Exception {
        return getDao().selectById(id);
    }
    
    public T selectOne(T t)throws Exception{
    
    	return getDao().selectOne(t);
    }
    
    
    /**
     * 根据条件查询数据集合
     * 
     * t:条件封装
     */
    public List<T> selectList(T t)throws Exception{
    		return getDao().selectList(t);
    }
    
    /**
     * 根据条件查询数据集合(分页查询)
     * 
     * t:条件封装
     */
    public List<T> selectListPage(PageBean pb,T t)throws Exception{
    	List<T> list=new ArrayList<T>();
    	Page<?> page=PageHelper.startPage(pb.getCurPage(), SystemConstant.PAGE_SIZE);
		list=getDao().selectList(t);
    	PageUtil.getPageNumber(pb, page);
    	return list;
    }
    
}
