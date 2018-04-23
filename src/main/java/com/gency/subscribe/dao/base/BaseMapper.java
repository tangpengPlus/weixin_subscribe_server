package com.gency.subscribe.dao.base;
import java.io.Serializable;
import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * 
 * 作者:唐鹏
 * 描述: Mybatis基础DAO
 * 版本: version 1.0.0
 * 时间: 2017年7月17日 下午3:47:27
 * @param <T>
 */
public interface BaseMapper<T> {

	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	T selectById(Serializable id);
	
	/**
	 * 查询单条记录
	 * @param entity
	 * @return
	 */
	T selectOne(@Param("item")T t);

	/**
	 * 查询记录集合
	 * @param entity
	 * @return
	 */
	List<T> selectList(@Param("item")T t);
	
	/**
	 * 查询记录集合(不需要条件)
	 * @param entity
	 * @return
	 */
	//List<T> selectListNoCondition();
	
	/**
	 * 通用的保存方法
	 * @param <T>
	 * @param entity
	 */
	int save(@Param("item")T t);
	
	
	/**
	 * 批量保存
	 * @param list
	 */
	int batchSave(List<T> list);

	/**
	 * 通用的修改方法
	 * @param <T>
	 * @param entity
	 */
	int update(@Param("item")T t);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	int batchUpdate(List<T> t);
	
	
	/**
	 * 删除方法
	 * @param id
	 */
	int delById(Serializable id);
	
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	int delList(List<T> list);

	/**
	 * 批量删除方法
	 * @param ids
	 */
	int delArray(int[] ids);
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述: 根据条件删除数据（逻辑删除）
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 上午11:08:25
	 * @param t
	 * @return
	 * int
	 */
	int delByCondition(@Param("item")T t);
	
	/**
	 * 
	 * 作者:唐鹏
	 * 描述:根据Id进行删除
	 * 版本: version 1.0.0
	 * 时间: 2017年8月23日 上午11:09:51
	 * @param id
	 * @return
	 * int
	 */
	int delById(@Param("id")Integer id);

	/**
	 * 统计查询
	 * @param <T>
	 * @param params 查询参数
	 * @return 总记录条数
	 */
	int count(T t);

}
