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
 * 设备Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizEquipment extends DataEntity<BizEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 设备名称
	private String type;		// 设备类型
	private String plant;		// 所属车间
	private String capacity;		// 设备能力
	private String status;		// 设备状态
	private String amortizationTime;		// 摊销时间
	private Date beginTime;		// 开始时间
	private Date endTime;		// 结束时间
	
	public BizEquipment() {
		this.delFlag = DEL_FLAG_AUDIT;
	}

	public BizEquipment(String id){
		this();
		this.id = id;
	}

	@Length(min=1, max=100, message="设备名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="设备类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=1, max=20, message="所属车间长度必须介于 1 和 20 之间")
	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}
	
	@Length(min=0, max=20, message="设备能力长度必须介于 0 和 20 之间")
	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	@Length(min=0, max=20, message="设备状态长度必须介于 0 和 20 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=20, message="摊销时间长度必须介于 0 和 20 之间")
	public String getAmortizationTime() {
		return amortizationTime;
	}

	public void setAmortizationTime(String amortizationTime) {
		this.amortizationTime = amortizationTime;
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