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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.online.itg.common.config.Global;
import com.online.itg.common.persistence.Page;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.entity.BizMaterielStatistics;
import com.online.itg.modules.biz.service.BizMaterielService;

/**
 * 物资Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizMateriel")
public class BizMaterielController extends BaseController {

	@Autowired
	private BizMaterielService bizMaterielService;
	
	@ModelAttribute
	public BizMateriel get(@RequestParam(required=false) String id) {
		BizMateriel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizMaterielService.get(id);
		}
		if (entity == null){
			entity = new BizMateriel();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizMateriel:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMateriel bizMateriel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<BizMateriel> page = bizMaterielService.findPage(new Page<BizMateriel>(request, response), bizMateriel); 
		model.addAttribute("page", page);
		return "modules/biz/bizMaterielList";
	}

	@ResponseBody
    @RequestMapping(value = "getStatisticsData")
    public List<BizMaterielStatistics> getStatisticsData(BizMateriel bizMateriel) {
		List<BizMaterielStatistics> statisticsList = bizMaterielService.findMaterielStatisticsByType(bizMateriel);
		return statisticsList;
    }
	
    @RequestMapping(value = "showStatisticsChart")
    public String showStatisticsChart(BizMateriel bizMateriel, Model model) {
    	model.addAttribute("bizMateriel", bizMateriel);
		return "modules/biz/bizMaterielStatistics";
    }
	
	@RequiresPermissions("biz:bizMateriel:view")
	@RequestMapping(value = "form")
	public String form(BizMateriel bizMateriel, Model model) {
		model.addAttribute("bizMateriel", bizMateriel);
		return "modules/biz/bizMaterielForm";
	}

	@RequiresPermissions("biz:bizMateriel:edit")
	@RequestMapping(value = "save")
	public String save(BizMateriel bizMateriel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMateriel)){
			return form(bizMateriel, model);
		}
		bizMaterielService.save(bizMateriel);
		addMessage(redirectAttributes, "保存物料成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizMateriel/?repage";
	}
	
	@RequiresPermissions("biz:bizMateriel:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMateriel bizMateriel, RedirectAttributes redirectAttributes) {
		bizMaterielService.delete(bizMateriel);
		addMessage(redirectAttributes, "删除物料成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizMateriel/?repage";
	}

}