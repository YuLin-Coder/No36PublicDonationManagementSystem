/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.online.itg.common.config.Global;
import com.online.itg.common.persistence.Page;
import com.online.itg.common.utils.SerialNumber;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.biz.entity.BizProduct;
import com.online.itg.modules.biz.entity.BizProductWarehouse;
import com.online.itg.modules.biz.service.BizProductService;
import com.online.itg.modules.biz.service.BizProductWarehouseService;
import com.online.itg.modules.sys.utils.UserUtils;

/**
 * 产品入库Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizProductWarehouse")
public class BizProductWarehouseController extends BaseController {

	@Autowired
	private BizProductWarehouseService bizProductWarehouseService;
	
	@Autowired
	private BizProductService bizProductService;
	
	@ModelAttribute
	public BizProductWarehouse get(@RequestParam(required=false) String id) {
		BizProductWarehouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProductWarehouseService.get(id);
		}
		if (entity == null){
			entity = new BizProductWarehouse();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizProductWarehouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProductWarehouse bizProductWarehouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizProduct> productList = bizProductService.findList(new BizProduct());
		model.addAttribute("productList", productList);
		Page<BizProductWarehouse> page = bizProductWarehouseService.findPage(new Page<BizProductWarehouse>(request, response), bizProductWarehouse); 
		model.addAttribute("page", page);
		return "modules/biz/bizProductWarehouseList";
	}

	@RequiresPermissions("biz:bizProductWarehouse:view")
	@RequestMapping(value = "form")
	public String form(BizProductWarehouse bizProductWarehouse, Model model) {
		List<BizProduct> productList = bizProductService.findList(new BizProduct());
		model.addAttribute("productList", productList);
		model.addAttribute("bizProductWarehouse", bizProductWarehouse);
		return "modules/biz/bizProductWarehouseForm";
	}

	@RequiresPermissions("biz:bizProductWarehouse:edit")
	@RequestMapping(value = "save")
	public String save(BizProductWarehouse bizProductWarehouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProductWarehouse)){
			return form(bizProductWarehouse, model);
		}
		//生成批次号
		String batchNum = SerialNumber.createSerial();
		bizProductWarehouse.setInBatch(batchNum);
		bizProductWarehouse.setStaffId(UserUtils.getUser().getId());
		bizProductWarehouseService.save(bizProductWarehouse);
		addMessage(redirectAttributes, "保存产品入库成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizProductWarehouse/?repage";
	}
	
	@RequiresPermissions("biz:bizProductWarehouse:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProductWarehouse bizProductWarehouse, RedirectAttributes redirectAttributes) {
		bizProductWarehouseService.delete(bizProductWarehouse);
		addMessage(redirectAttributes, "删除产品入库成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizProductWarehouse/?repage";
	}

}