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
import com.online.itg.common.utils.StringUtils;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.biz.entity.BizProduct;
import com.online.itg.modules.biz.entity.BizProductionPlan;
import com.online.itg.modules.biz.service.BizProductService;
import com.online.itg.modules.biz.service.BizProductionPlanService;
import com.online.itg.modules.cms.entity.Guestbook;

/**
 * 生产计划Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizProductionPlan")
public class BizProductionPlanController extends BaseController {

	@Autowired
	private BizProductionPlanService bizProductionPlanService;
	
	@Autowired
	private BizProductService bizProductService;
	
	@ModelAttribute
	public BizProductionPlan get(@RequestParam(required=false) String id) {
		BizProductionPlan entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizProductionPlanService.get(id);
		}
		if (entity == null){
			entity = new BizProductionPlan();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizProductionPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizProductionPlan bizProductionPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizProductionPlan> page = bizProductionPlanService.findPage(new Page<BizProductionPlan>(request, response), bizProductionPlan); 
		List<BizProduct> productList = bizProductService.findList(new BizProduct());
		model.addAttribute("productList", productList);
		model.addAttribute("page", page);
		return "modules/biz/bizProductionPlanList";
	}

	/**
	 * 
	 * @param bizProductionPlan
	 * @param model
	 * @param auditFlag 0为编辑，1为审核
	 * @return
	 */
	@RequiresPermissions("biz:bizProductionPlan:view")
	@RequestMapping(value = "form")
	public String form(BizProductionPlan bizProductionPlan, Model model, String auditFlag) {
		List<BizProduct> productList = bizProductService.findList(new BizProduct());
		model.addAttribute("productList", productList);
		model.addAttribute("bizProductionPlan", bizProductionPlan);
		model.addAttribute("auditFlag", auditFlag);
		return "modules/biz/bizProductionPlanForm";
	}

	@RequiresPermissions("biz:bizProductionPlan:edit")
	@RequestMapping(value = "save")
	public String save(BizProductionPlan bizProductionPlan, Model model, RedirectAttributes redirectAttributes) {
		String promptMsg = "";
		String returnAdd = "";
		
		if (!beanValidator(model, bizProductionPlan)){
			return form(bizProductionPlan, model, "0");
		}
		if (StringUtils.isNotEmpty(bizProductionPlan.getRemarks())){
			promptMsg = "审核" + bizProductionPlan.getName() + "成功";
			returnAdd = "redirect:"+Global.getAdminPath()+"/biz/bizProductionPlan/audit/?repage";
		}else {
			promptMsg = "保存" + bizProductionPlan.getName() + "成功";
			returnAdd = "redirect:"+Global.getAdminPath()+"/biz/bizProductionPlan/?repage";
		}
		
		bizProductionPlanService.save(bizProductionPlan);
		addMessage(redirectAttributes, promptMsg);
		return returnAdd;
	}
	
	@RequiresPermissions("biz:bizProductionPlan:view")
	@RequestMapping(value = "audit")
	public String audit(BizProductionPlan bizProductionPlan, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizProductionPlan> page = bizProductionPlanService.findPage(new Page<BizProductionPlan>(request, response), bizProductionPlan); 
		List<BizProduct> productList = bizProductService.findList(new BizProduct());
		model.addAttribute("productList", productList);
		model.addAttribute("page", page);
		return "modules/biz/bizProductionPlanAudit";
	}
	
	@RequiresPermissions("biz:bizProductionPlan:edit")
	@RequestMapping(value = "delete")
	public String delete(BizProductionPlan bizProductionPlan, @RequestParam(required=false) Boolean isRe,RedirectAttributes redirectAttributes) {
		String delFlag = isRe!=null&&isRe ? Guestbook.DEL_FLAG_AUDIT : Guestbook.DEL_FLAG_DELETE;
		bizProductionPlan.setDelFlag(delFlag);
		bizProductionPlanService.delete(bizProductionPlan);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"恢复审核":"删除")+"生产计划成功");
		
		return "redirect:"+Global.getAdminPath()+"/biz/bizProductionPlan/audit?repage";
	}

}