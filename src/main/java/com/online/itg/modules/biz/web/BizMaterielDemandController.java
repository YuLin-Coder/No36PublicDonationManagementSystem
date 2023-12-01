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
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.entity.BizMaterielDemand;
import com.online.itg.modules.biz.service.BizMaterielDemandService;
import com.online.itg.modules.biz.service.BizMaterielService;
import com.online.itg.modules.cms.entity.Guestbook;

/**
 * 物料需求Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizMaterielDemand")
public class BizMaterielDemandController extends BaseController {

	@Autowired
	private BizMaterielDemandService bizMaterielDemandService;
	
	@Autowired
	private BizMaterielService bizMaterielService;
	
	@ModelAttribute
	public BizMaterielDemand get(@RequestParam(required=false) String id) {
		BizMaterielDemand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizMaterielDemandService.get(id);
		}
		if (entity == null){
			entity = new BizMaterielDemand();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizMaterielDemand:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterielDemand bizMaterielDemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		Page<BizMaterielDemand> page = bizMaterielDemandService.findPage(new Page<BizMaterielDemand>(request, response), bizMaterielDemand); 
		model.addAttribute("page", page);
		return "modules/biz/bizMaterielDemandList";
	}

	/**
	 * 
	 * @param bizMaterielDemand
	 * @param model
	 * @param auditFlag  0为编辑，1为审核
	 * @return
	 */
	@RequiresPermissions("biz:bizMaterielDemand:view")
	@RequestMapping(value = "form")
	public String form(BizMaterielDemand bizMaterielDemand, Model model, String auditFlag) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		model.addAttribute("bizMaterielDemand", bizMaterielDemand);
		model.addAttribute("auditFlag", auditFlag);
		return "modules/biz/bizMaterielDemandForm";
	}

	@RequiresPermissions("biz:bizMaterielDemand:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterielDemand bizMaterielDemand, Model model, RedirectAttributes redirectAttributes) {
		String promptMsg = "";
		String returnAdd = "";
		
		if (!beanValidator(model, bizMaterielDemand)){
			return form(bizMaterielDemand, model, "0");
		}
		if (StringUtils.isNotEmpty(bizMaterielDemand.getRemarks())){
			promptMsg = "审核" + bizMaterielDemand.getId() + "成功";
			returnAdd = "redirect:"+Global.getAdminPath()+"/biz/bizMaterielDemand/audit/?repage";
		}else {
			promptMsg = "保存" + bizMaterielDemand.getId() + "成功";
			returnAdd = "redirect:"+Global.getAdminPath()+"/biz/bizMaterielDemand/?repage";
		}
		
		bizMaterielDemandService.save(bizMaterielDemand);
		addMessage(redirectAttributes, promptMsg);
		return returnAdd;
	}
	
	@RequiresPermissions("biz:bizMaterielDemand:edit")
	@RequestMapping(value = "audit")
	public String audit(BizMaterielDemand bizMaterielDemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		Page<BizMaterielDemand> page = bizMaterielDemandService.findPage(new Page<BizMaterielDemand>(request, response), bizMaterielDemand); 
		model.addAttribute("page", page);
		return "modules/biz/bizMaterielDemandAudit";
	}
	
	@RequiresPermissions("biz:bizMaterielDemand:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterielDemand bizMaterielDemand, @RequestParam(required=false) Boolean isRe, RedirectAttributes redirectAttributes) {
		String delFlag = isRe!=null&&isRe ? Guestbook.DEL_FLAG_AUDIT : Guestbook.DEL_FLAG_DELETE;
		bizMaterielDemand.setDelFlag(delFlag);
		bizMaterielDemandService.delete(bizMaterielDemand);
		addMessage(redirectAttributes, (isRe!=null&&isRe?"恢复审核":"删除")+"物料需求成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizMaterielDemand/audit?repage";
	}

}