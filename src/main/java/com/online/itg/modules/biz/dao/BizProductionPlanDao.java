/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.dao;

import com.online.itg.common.persistence.CrudDao;
import com.online.itg.common.persistence.annotation.MyBatisDao;
import com.online.itg.modules.biz.entity.BizProductionPlan;

/**
 * 生产计划DAO接口
 * @author yxm
 * @version 2018-04-01
 */
@MyBatisDao
public interface BizProductionPlanDao extends CrudDao<BizProductionPlan> {
	
}