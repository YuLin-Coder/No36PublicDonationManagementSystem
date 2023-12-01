/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.service.CrudService;
import com.online.itg.modules.biz.entity.BizProduct;
import com.online.itg.modules.biz.dao.BizProductDao;

/**
 * 产品Service
 * @author yxm
 * @version 2018-04-01
 */
@Service
@Transactional(readOnly = true)
public class BizProductService extends CrudService<BizProductDao, BizProduct> {

	public BizProduct get(String id) {
		return super.get(id);
	}
	
	public List<BizProduct> findList(BizProduct bizProduct) {
		return super.findList(bizProduct);
	}
	
	public Page<BizProduct> findPage(Page<BizProduct> page, BizProduct bizProduct) {
		return super.findPage(page, bizProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(BizProduct bizProduct) {
		super.save(bizProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(BizProduct bizProduct) {
		super.delete(bizProduct);
	}
	
}