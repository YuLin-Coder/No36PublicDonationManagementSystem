/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizDrawMateriel;
import com.online.itg.modules.biz.dao.BizDrawMaterielDao;

/**
 * 领料Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizDrawMaterielService extends CrudService<BizDrawMaterielDao, BizDrawMateriel> {

	public BizDrawMateriel get(String id) {
		return super.get(id);
	}
	
	public List<BizDrawMateriel> findList(BizDrawMateriel bizDrawMateriel) {
		return super.findList(bizDrawMateriel);
	}
	
	public Page<BizDrawMateriel> findPage(Page<BizDrawMateriel> page, BizDrawMateriel bizDrawMateriel) {
		return super.findPage(page, bizDrawMateriel);
	}
	
	@Transactional(readOnly = false)
	public void save(BizDrawMateriel bizDrawMateriel) {
		super.save(bizDrawMateriel);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizDrawMateriel bizDrawMateriel) {
		super.delete(bizDrawMateriel);
	}
	
}