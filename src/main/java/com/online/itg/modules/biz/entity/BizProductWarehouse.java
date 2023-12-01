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
 * 产品入库Entity
 * @author yxm
 * @version 2018-04-01
 */
public class BizProductWarehouse extends DataEntity<BizProductWarehouse> {
	
	private static final long serialVersionUID = 1L;
	private String warehouseId;		// 仓库ID
	private BizProduct bizProduct;		// 产品ID
	private String price;		// 单价
	private String productMark;		// 产品编号
	private String inBatch;		// 入库批次
	private String inNumber;		// 入库数量
	private Date inTime;		// 入库时间
	private String staffId;		// 操作员
	private String auditerId;		// 审核人
	
	public BizProductWarehouse() {
		super();
	}

	public BizProductWarehouse(String id){
		super(id);
	}

	@Length(min=1, max=100, message="仓库ID长度必须介于 1 和 100 之间")
	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	public BizProduct getBizProduct() {
		return bizProduct;
	}

	public void setBizProduct(BizProduct bizProduct) {
		this.bizProduct = bizProduct;
	}

	@Length(min=0, max=11, message="单价长度必须介于 0 和 11 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=1, max=100, message="产品编号长度必须介于 1 和 100 之间")
	public String getProductMark() {
		return productMark;
	}

	public void setProductMark(String productMark) {
		this.productMark = productMark;
	}
	
	@Length(min=0, max=20, message="入库批次长度必须介于 0 和 20 之间")
	public String getInBatch() {
		return inBatch;
	}

	public void setInBatch(String inBatch) {
		this.inBatch = inBatch;
	}
	
	@Length(min=0, max=11, message="入库数量长度必须介于 0 和 11 之间")
	public String getInNumber() {
		return inNumber;
	}

	public void setInNumber(String inNumber) {
		this.inNumber = inNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="入库时间不能为空")
	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	
	@Length(min=0, max=20, message="操作员长度必须介于 0 和 20 之间")
	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	
	@Length(min=0, max=20, message="审核人长度必须介于 0 和 20 之间")
	public String getAuditerId() {
		return auditerId;
	}

	public void setAuditerId(String auditerId) {
		this.auditerId = auditerId;
	}
	
}