/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizMaterielDemand;
import com.online.itg.modules.biz.dao.BizMaterielDemandDao;

/**
 * 物料需求Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizMaterielDemandService extends CrudService<BizMaterielDemandDao, BizMaterielDemand> {

	public BizMaterielDemand get(String id) {
		return super.get(id);
	}
	
	public List<BizMaterielDemand> findList(BizMaterielDemand bizMaterielDemand) {
		return super.findList(bizMaterielDemand);
	}
	
	public Page<BizMaterielDemand> findPage(Page<BizMaterielDemand> page, BizMaterielDemand bizMaterielDemand) {
		return super.findPage(page, bizMaterielDemand);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMaterielDemand bizMaterielDemand) {
		super.save(bizMaterielDemand);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMaterielDemand bizMaterielDemand) {
		super.delete(bizMaterielDemand);
	}
	
}