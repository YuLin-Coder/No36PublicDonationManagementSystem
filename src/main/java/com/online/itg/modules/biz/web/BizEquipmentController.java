/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.online.itg.common.config.Global;
import com.online.itg.common.persistence.Page;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.biz.entity.BizEquipment;
import com.online.itg.modules.biz.service.BizEquipmentService;

/**
 * 设备Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizEquipment")
public class BizEquipmentController extends BaseController {

	@Autowired
	private BizEquipmentService bizEquipmentService;
	
	@ModelAttribute
	public BizEquipment get(@RequestParam(required=false) String id) {
		BizEquipment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizEquipmentService.get(id);
		}
		if (entity == null){
			entity = new BizEquipment();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizEquipment bizEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizEquipment.setDelFlag(BizEquipment.DEL_FLAG_NORMAL);
		Page<BizEquipment> page = bizEquipmentService.findPage(new Page<BizEquipment>(request, response), bizEquipment); 
		model.addAttribute("page", page);
		return "modules/biz/bizEquipmentList";
	}
	
	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = "manage")
	public String manage(BizEquipment bizEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizEquipment.setDelFlag(BizEquipment.DEL_FLAG_AUDIT);
		Page<BizEquipment> page = bizEquipmentService.findPage(new Page<BizEquipment>(request, response), bizEquipment); 
		model.addAttribute("page", page);
		return "modules/biz/bizEquipmentManage";
	}
	
	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = "showRepairEquipment")
	public String showRepairEquipment(BizEquipment bizEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizEquipment.setDelFlag(BizEquipment.DEL_FLAG_NORMAL);
		bizEquipment.setStatus("12");//维修
		Page<BizEquipment> page = bizEquipmentService.findPage(new Page<BizEquipment>(request, response), bizEquipment); 
		model.addAttribute("page", page);
		return "modules/biz/bizEquipmentFaultList";
	}
	
	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = "showScrapEquipment")
	public String showScrapEquipment(BizEquipment bizEquipment, HttpServletRequest request, HttpServletResponse response, Model model) {
		bizEquipment.setDelFlag(BizEquipment.DEL_FLAG_NORMAL);
		bizEquipment.setStatus("13");//报废
		Page<BizEquipment> page = bizEquipmentService.findPage(new Page<BizEquipment>(request, response), bizEquipment); 
		model.addAttribute("page", page);
		return "modules/biz/bizEquipmentFaultList";
	}

	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = "form")
	public String form(BizEquipment bizEquipment, Model model) {
		model.addAttribute("bizEquipment", bizEquipment);
		return "modules/biz/bizEquipmentForm";
	}
	
	@RequiresPermissions("biz:bizEquipment:view")
	@RequestMapping(value = "apply")
	public String apply(BizEquipment bizEquipment, Model model) {
		model.addAttribute("bizEquipment", bizEquipment);
		model.addAttribute("bizEquipmentId", bizEquipment.getId());
		return "modules/biz/bizEquipmentApply";
	}

	@RequiresPermissions("biz:bizEquipment:edit")
	@RequestMapping(value = "save")
	public String save(BizEquipment bizEquipment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizEquipment)){
			return form(bizEquipment, model);
		}
		bizEquipmentService.save(bizEquipment);
		addMessage(redirectAttributes, "保存设备成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizEquipment/?repage";
	}
	
	@ResponseBody
	@RequestMapping(value = "saveBizEquipment")
	public Map<String,String> saveBizEquipment(BizEquipment bizEquipment, Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "1");
		try {
			bizEquipment.setDelFlag(BizEquipment.DEL_FLAG_NORMAL);
			bizEquipmentService.save(bizEquipment);
			map.put("result", "0");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorMsg", e.toString());
		}
		return map;
	}
	
	@RequiresPermissions("biz:bizEquipment:edit")
	@RequestMapping(value = "delete")
	public String delete(BizEquipment bizEquipment, RedirectAttributes redirectAttributes) {
		bizEquipmentService.delete(bizEquipment);
		addMessage(redirectAttributes, "删除设备成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizEquipment/?repage";
	}

}