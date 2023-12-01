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
 * 物料需求Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizMaterielDemand extends DataEntity<BizMaterielDemand> {
	
	private static final long serialVersionUID = 1L;
	private BizMateriel bizMateriel;		// 物料ID
	private String existingNumber;		// 库存数量
	private String projectNumber;		// 计划数量
	private String emergency;		// 紧急度
	private String plantId;		// 车间ID
	private Date deliveryTime;		// 交货时间
	
	public BizMaterielDemand() {
		this.delFlag = DEL_FLAG_AUDIT;
	}

	public BizMaterielDemand(String id){
		this();
		this.id = id;
	}
	
	public BizMateriel getBizMateriel() {
		return bizMateriel;
	}

	public void setBizMateriel(BizMateriel bizMateriel) {
		this.bizMateriel = bizMateriel;
	}

	@Length(min=0, max=11, message="库存数量长度必须介于 0 和 11 之间")
	public String getExistingNumber() {
		return existingNumber;
	}

	public void setExistingNumber(String existingNumber) {
		this.existingNumber = existingNumber;
	}
	
	@Length(min=1, max=20, message="计划数量长度必须介于 1 和 20 之间")
	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}
	
	@Length(min=0, max=20, message="紧急度长度必须介于 0 和 20 之间")
	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}
	
	@Length(min=0, max=20, message="车间ID长度必须介于 0 和 20 之间")
	public String getPlantId() {
		return plantId;
	}

	public void setPlantId(String plantId) {
		this.plantId = plantId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="交货时间不能为空")
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
}