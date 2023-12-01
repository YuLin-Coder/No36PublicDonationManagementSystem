/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.entity;

import com.online.itg.common.persistence.DataEntity;

/**
 * 物资统计Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizMaterielStatistics extends DataEntity<BizMaterielStatistics> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 物资名称
	private String priceSum;		// 利润和
	private String supplyOrigin;		// 类型
	
	public BizMaterielStatistics() {
		super();
	}

	public BizMaterielStatistics(String id){
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPriceSum() {
		return priceSum;
	}

	public void setPriceSum(String priceSum) {
		this.priceSum = priceSum;
	}

	public String getSupplyOrigin() {
		return supplyOrigin;
	}

	public void setSupplyOrigin(String supplyOrigin) {
		this.supplyOrigin = supplyOrigin;
	}
	
}