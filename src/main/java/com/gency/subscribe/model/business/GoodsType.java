package com.gency.subscribe.model.business;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.gency.subscribe.model.base.BaseModel;

/**
	* 作者:唐鹏
	* 描述:
	* 版本: version 1.0.0
	* 时间:2018-04-23 10:45:00
  */
public class GoodsType extends BaseModel {
    /**
     * 数据编号
     * 表字段 : goods_type.id
     */
    private Integer id;

    /**
     * 商品类别名称
     * 表字段 : goods_type.name
     */
    @NotEmpty(message="商品类别名称不能为空")
    @Length(max=50,message="商品类别名称太长")
    private String name;

    /**
     * 0:显示 1:删除
     * 表字段 : goods_type.is_delete
     */
    private Byte isDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table goods_type
     *
     * @mbg.generated Mon Apr 23 10:45:00 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 数据编号 字段:goods_type.id
     *
     * @return goods_type.id, 数据编号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 数据编号 字段:goods_type.id
     *
     * @param id the value for goods_type.id, 数据编号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 商品类别名称 字段:goods_type.name
     *
     * @return goods_type.name, 商品类别名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置 商品类别名称 字段:goods_type.name
     *
     * @param name the value for goods_type.name, 商品类别名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取 0:显示 1:删除 字段:goods_type.is_delete
     *
     * @return goods_type.is_delete, 0:显示 1:删除
     */
    public Byte getIsDelete() {
        return isDelete;
    }

    /**
     * 设置 0:显示 1:删除 字段:goods_type.is_delete
     *
     * @param isDelete the value for goods_type.is_delete, 0:显示 1:删除
     */
    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }
}