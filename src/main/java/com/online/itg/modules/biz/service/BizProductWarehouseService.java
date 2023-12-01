/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizProductWarehouse;
import com.online.itg.modules.biz.dao.BizProductWarehouseDao;

/**
 * 产品入库Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizProductWarehouseService extends CrudService<BizProductWarehouseDao, BizProductWarehouse> {

	public BizProductWarehouse get(String id) {
		return super.get(id);
	}
	
	public List<BizProductWarehouse> findList(BizProductWarehouse bizProductWarehouse) {
		return super.findList(bizProductWarehouse);
	}
	
	public Page<BizProductWarehouse> findPage(Page<BizProductWarehouse> page, BizProductWarehouse bizProductWarehouse) {
		return super.findPage(page, bizProductWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProductWarehouse bizProductWarehouse) {
		super.save(bizProductWarehouse);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProductWarehouse bizProductWarehouse) {
		super.delete(bizProductWarehouse);
	}
	
}