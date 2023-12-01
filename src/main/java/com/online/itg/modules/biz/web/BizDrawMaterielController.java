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
import com.online.itg.common.web.BaseController;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.modules.biz.entity.BizDrawMateriel;
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.service.BizDrawMaterielService;
import com.online.itg.modules.biz.service.BizMaterielService;

/**
 * 领料Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizDrawMateriel")
public class BizDrawMaterielController extends BaseController {

	@Autowired
	private BizDrawMaterielService bizDrawMaterielService;
	
	@Autowired
	private BizMaterielService bizMaterielService;
	
	@ModelAttribute
	public BizDrawMateriel get(@RequestParam(required=false) String id) {
		BizDrawMateriel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizDrawMaterielService.get(id);
		}
		if (entity == null){
			entity = new BizDrawMateriel();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizDrawMateriel:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizDrawMateriel bizDrawMateriel, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		Page<BizDrawMateriel> page = bizDrawMaterielService.findPage(new Page<BizDrawMateriel>(request, response), bizDrawMateriel); 
		model.addAttribute("page", page);
		return "modules/biz/bizDrawMaterielList";
	}

	@RequiresPermissions("biz:bizDrawMateriel:view")
	@RequestMapping(value = "form")
	public String form(BizDrawMateriel bizDrawMateriel, Model model) {
		model.addAttribute("bizDrawMateriel", bizDrawMateriel);
		return "modules/biz/bizDrawMaterielForm";
	}

	@RequiresPermissions("biz:bizDrawMateriel:edit")
	@RequestMapping(value = "save")
	public String save(BizDrawMateriel bizDrawMateriel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizDrawMateriel)){
			return form(bizDrawMateriel, model);
		}
		bizDrawMaterielService.save(bizDrawMateriel);
		addMessage(redirectAttributes, "保存领料成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizDrawMateriel/?repage";
	}
	
	@RequiresPermissions("biz:bizDrawMateriel:edit")
	@RequestMapping(value = "delete")
	public String delete(BizDrawMateriel bizDrawMateriel, RedirectAttributes redirectAttributes) {
		bizDrawMaterielService.delete(bizDrawMateriel);
		addMessage(redirectAttributes, "删除领料成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizDrawMateriel/?repage";
	}

}