/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizEquipment;
import com.online.itg.modules.biz.dao.BizEquipmentDao;

/**
 * 设备Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizEquipmentService extends CrudService<BizEquipmentDao, BizEquipment> {

	public BizEquipment get(String id) {
		return super.get(id);
	}
	
	public List<BizEquipment> findList(BizEquipment bizEquipment) {
		return super.findList(bizEquipment);
	}
	
	public Page<BizEquipment> findPage(Page<BizEquipment> page, BizEquipment bizEquipment) {
		return super.findPage(page, bizEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(BizEquipment bizEquipment) {
		super.save(bizEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizEquipment bizEquipment) {
		super.delete(bizEquipment);
	}
	
}