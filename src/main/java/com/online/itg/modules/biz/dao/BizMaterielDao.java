/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.dao;

import java.util.List;

import com.online.itg.common.persistence.CrudDao;
import com.online.itg.common.persistence.annotation.MyBatisDao;
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.entity.BizMaterielStatistics;

/**
 * 物料DAO接口
 * @author yxm
 * @version 2018-04-01
 */
@MyBatisDao
public interface BizMaterielDao extends CrudDao<BizMateriel> {
	List<BizMaterielStatistics> findMaterielStatisticsByType(BizMateriel bizMateriel);
}