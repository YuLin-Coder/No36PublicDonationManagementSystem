/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.web;

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
import com.online.itg.common.web.BaseController;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.modules.biz.entity.BizProduct;
import com.online.itg.modules.biz.service.BizProductService;

/**
 * 产品Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizProduct")
public class BizProductController extends BaseController {

	@Autowired
	private BizProductService bizProductService;
	
	@ModelAttribute
	public BizProduct get(@RequestParam(required=false) String id) {
		BizProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProductService.get(id);
		}
		if (entity == null){
			entity = new BizProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProduct bizProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizProduct> page = bizProductService.findPage(new Page<BizProduct>(request, response), bizProduct); 
		model.addAttribute("page", page);
		return "modules/biz/bizProductList";
	}

	@RequiresPermissions("biz:bizProduct:view")
	@RequestMapping(value = "form")
	public String form(BizProduct bizProduct, Model model) {
		model.addAttribute("bizProduct", bizProduct);
		return "modules/biz/bizProductForm";
	}

	@RequiresPermissions("biz:bizProduct:edit")
	@RequestMapping(value = "save")
	public String save(BizProduct bizProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizProduct)){
			return form(bizProduct, model);
		}
		bizProductService.save(bizProduct);
		addMessage(redirectAttributes, "保存产品成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizProduct/?repage";
	}
	
	@RequiresPermissions("biz:bizProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProduct bizProduct, RedirectAttributes redirectAttributes) {
		bizProductService.delete(bizProduct);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizProduct/?repage";
	}

}