/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizProductionPlan;
import com.online.itg.modules.biz.dao.BizProductionPlanDao;

/**
 * 生产计划Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizProductionPlanService extends CrudService<BizProductionPlanDao, BizProductionPlan> {

	public BizProductionPlan get(String id) {
		return super.get(id);
	}
	
	public List<BizProductionPlan> findList(BizProductionPlan bizProductionPlan) {
		return super.findList(bizProductionPlan);
	}
	
	public Page<BizProductionPlan> findPage(Page<BizProductionPlan> page, BizProductionPlan bizProductionPlan) {
		return super.findPage(page, bizProductionPlan);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProductionPlan bizProductionPlan) {
		super.save(bizProductionPlan);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProductionPlan bizProductionPlan) {
		super.delete(bizProductionPlan);
	}
	
}