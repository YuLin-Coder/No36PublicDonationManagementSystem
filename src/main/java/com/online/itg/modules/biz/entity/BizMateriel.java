/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.entity;

import org.hibernate.validator.constraints.Length;

import com.online.itg.common.persistence.DataEntity;

/**
 * 物资Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizMateriel extends DataEntity<BizMateriel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 物资名称
	private String price;		// 单价
	private String description;		// 物料描述
	private String productArea;		// 产地
	private String supplyOrigin;		// 类型
	
	public BizMateriel() {
		super();
	}

	public BizMateriel(String id){
		super(id);
	}

	@Length(min=1, max=100, message="物料名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="单价长度必须介于 0 和 11 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=200, message="物料描述长度必须介于 0 和 200 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=200, message="产地长度必须介于 0 和 200 之间")
	public String getProductArea() {
		return productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}
	
	@Length(min=0, max=200, message="供货来源长度必须介于 0 和 200 之间")
	public String getSupplyOrigin() {
		return supplyOrigin;
	}

	public void setSupplyOrigin(String supplyOrigin) {
		this.supplyOrigin = supplyOrigin;
	}
	
}