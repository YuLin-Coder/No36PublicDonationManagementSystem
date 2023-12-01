/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.entity;

import org.hibernate.validator.constraints.Length;

import com.online.itg.common.persistence.DataEntity;

/**
 * 产品Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizProduct extends DataEntity<BizProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String model;		// 型号
	private String area;		// 型号
	private String introduce;		// 简介
	
	public BizProduct() {
		super();
	}

	public BizProduct(String id){
		super(id);
	}

	@Length(min=1, max=100, message="产品名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=100, message="产品型号长度必须介于 1 和 100 之间")
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Length(min=0, max=100, message="产品简介长度必须介于 0 和 100 之间")
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
}