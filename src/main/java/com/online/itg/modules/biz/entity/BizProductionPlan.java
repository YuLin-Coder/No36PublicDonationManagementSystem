/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.online.itg.common.persistence.DataEntity;

/**
 * 生产计划Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizProductionPlan extends DataEntity<BizProductionPlan> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 计划名称
	private String type;		// 计划类型
	private String status;		// 计划状态
	private String existingNumber;		// 库存数量
	private String projectNumber;		// 计划生产数量
	private BizProduct bizProduct;		// 产品ID
	private String plantId;		// 车间ID
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	
	public BizProductionPlan() {
		this.delFlag = DEL_FLAG_AUDIT;
	}

	public BizProductionPlan(String id){
		this();
		this.id = id;
	}

	@Length(min=1, max=100, message="计划名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=100, message="计划类型长度必须介于 1 和 100 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=20, message="计划状态长度必须介于 0 和 20 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=11, message="库存数量长度必须介于 0 和 11 之间")
	public String getExistingNumber() {
		return existingNumber;
	}

	public void setExistingNumber(String existingNumber) {
		this.existingNumber = existingNumber;
	}
	
	@Length(min=1, max=11, message="计划生产数量长度必须介于 1 和 11 之间")
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	public BizProduct getBizProduct() {
		return bizProduct;
	}

	public void setBizProduct(BizProduct bizProduct) {
		this.bizProduct = bizProduct;
	}

	@Length(min=0, max=64, message="车间ID长度必须介于 0 和64 之间")
	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始时间不能为空")
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="结束时间不能为空")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}