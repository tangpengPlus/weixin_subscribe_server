package com.gency.subscribe.service.base;

import java.io.Serializable;
import java.util.List;

import com.gency.subscribe.model.system.PageBean;
/**
 * 
 * 作者:唐鹏
 * 描述: 接口父类 封装增删改查接口
 * 版本: version 1.0.0
 * 时间: 2017年7月17日 下午1:49:54
 * @param <T> 泛型
 */
public interface GenericService<T> {
	 /**
     * 插入对象
     *
     * @param t 对象
     */
	int save(T t)throws Exception;

	/**
     * 批量插入对象
     *
     * @param t 对象
     */
    int batchSave(List<T> t)throws Exception;
    
    /**
     * 更新对象
     *
     * @param t 对象
     */
    int update(T t)throws Exception;
    
    /**
     * 批量更新对象
     *
     * @param list 对象
     */
    int batchUpdate(List<T> list)throws Exception;

    /**
     * 通过主键, 删除对象
     *
     * @param id 主键
     */
    int delById(Serializable id)throws Exception;
    
    /**
     * 批量删除数据(集合对象)
     *
     * @param id 主键
     */
    int delList(List<T> list)throws Exception;
    
    /**
     * 批量删除数据 (数组对象)
     *
     * @param id 主键
     */
    int delArray(int[] ids)throws Exception;
    
    /**
     * 根据条件进行删除操作
     *
     * @param id 主键
     * @return T 对象
     */
    int delByCondition(T t)throws Exception;
    
    /**
     * 根据主键进行删除
     *
     * @param id 主键
     * @return T 对象
     */
    int delById(Integer id)throws Exception;
    /**
     * 统计查询
     *
     * @param id 主键
     */
    int count(T t)throws Exception;

    /**
     * 通过主键, 查询对象
     *
     * @param id 主键
     * @return T 对象
     */
    T selectById(Serializable id)throws Exception;
    
    /**
     * 条件查询一个对象
     *
     * @param t 查询条件封装
     * @return T  对象
     */
    T selectOne(T t)throws Exception;
    
    /**
     * 条件查询一个集合对象
     *
     * @param t 查询条件封装
     * @return list  对象集合
     */
    List<T> selectList(T t)throws Exception;
    
    /**
     * 条件查询一个集合对象
     *
     * @param pb:分页条件封装
     * @param t 查询条件封装
     * @param session 当前会话对象
     * @return list  对象集合
     */
    List<T> selectListPage(PageBean pb,T t)throws Exception;
    

}
