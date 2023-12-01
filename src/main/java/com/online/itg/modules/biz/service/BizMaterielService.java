/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.entity.BizMaterielStatistics;
import com.online.itg.modules.biz.dao.BizMaterielDao;

/**
 * 物料Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizMaterielService extends CrudService<BizMaterielDao, BizMateriel> {
	
	@Autowired
	private BizMaterielDao dao;

	public BizMateriel get(String id) {
		return super.get(id);
	}
	
	public List<BizMateriel> findList(BizMateriel bizMateriel) {
		return super.findList(bizMateriel);
	}
	
	public Page<BizMateriel> findPage(Page<BizMateriel> page, BizMateriel bizMateriel) {
		return super.findPage(page, bizMateriel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizMateriel bizMateriel) {
		super.save(bizMateriel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizMateriel bizMateriel) {
		super.delete(bizMateriel);
	}
	
	public List<BizMaterielStatistics> findMaterielStatisticsByType(BizMateriel bizMateriel) {
		return dao.findMaterielStatisticsByType(bizMateriel);
	}
}