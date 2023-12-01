/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizMaterielWarehouse;
import com.online.itg.modules.biz.dao.BizMaterielWarehouseDao;

/**
 * 物料入库Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizMaterielWarehouseService extends CrudService<BizMaterielWarehouseDao, BizMaterielWarehouse> {

	public BizMaterielWarehouse get(String id) {
		return super.get(id);
	}
	
	public List<BizMaterielWarehouse> findList(BizMaterielWarehouse bizMaterielWarehouse) {
		return super.findList(bizMaterielWarehouse);
	}
	
	public Page<BizMaterielWarehouse> findPage(Page<BizMaterielWarehouse> page, BizMaterielWarehouse bizMaterielWarehouse) {
		return super.findPage(page, bizMaterielWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterielWarehouse bizMaterielWarehouse) {
		super.save(bizMaterielWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterielWarehouse bizMaterielWarehouse) {
		super.delete(bizMaterielWarehouse);
	}
	
}