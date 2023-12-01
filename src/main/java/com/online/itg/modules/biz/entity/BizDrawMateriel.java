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
 * 领料Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizDrawMateriel extends DataEntity<BizDrawMateriel> {
	
	private static final long serialVersionUID = 1L;
	private BizMateriel bizMateriel;		// 物料ID
	private String drawBatch;		// 领料批次
	private String drawNumber;		// 领料数量
	private Date drawTime;		// 领料时间
	private String staffId;		// 领料人
	private String drawExplain;		// 领料说明
	
	public BizDrawMateriel() {
		super();
	}

	public BizDrawMateriel(String id){
		super(id);
	}

	public BizMateriel getBizMateriel() {
		return bizMateriel;
	}

	public void setBizMateriel(BizMateriel bizMateriel) {
		this.bizMateriel = bizMateriel;
	}

	@Length(min=0, max=20, message="领料批次长度必须介于 0 和 20 之间")
	public String getDrawBatch() {
		return drawBatch;
	}

	public void setDrawBatch(String drawBatch) {
		this.drawBatch = drawBatch;
	}
	
	@Length(min=0, max=11, message="领料数量长度必须介于 0 和 11 之间")
	public String getDrawNumber() {
		return drawNumber;
	}

	public void setDrawNumber(String drawNumber) {
		this.drawNumber = drawNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="领料时间不能为空")
	public Date getDrawTime() {
		return drawTime;
	}

	public void setDrawTime(Date drawTime) {
		this.drawTime = drawTime;
	}
	
	@Length(min=0, max=20, message="领料人长度必须介于 0 和 20 之间")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	@Length(min=0, max=500, message="领料说明长度必须介于 0 和 500 之间")
	public String getDrawExplain() {
		return drawExplain;
	}

	public void setDrawExplain(String drawExplain) {
		this.drawExplain = drawExplain;
	}
	
}