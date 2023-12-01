/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.biz.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.online.itg.common.utils.SerialNumber;
import com.online.itg.common.utils.StringUtils;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.biz.entity.BizDrawMateriel;
import com.online.itg.modules.biz.entity.BizMateriel;
import com.online.itg.modules.biz.entity.BizMaterielWarehouse;
import com.online.itg.modules.biz.service.BizDrawMaterielService;
import com.online.itg.modules.biz.service.BizMaterielService;
import com.online.itg.modules.biz.service.BizMaterielWarehouseService;
import com.online.itg.modules.sys.utils.UserUtils;

/**
 * 物料入库Controller
 * @author yxm
 * @version 2018-04-01
 */
@Controller
@RequestMapping(value = "${adminPath}/biz/bizMaterielWarehouse")
public class BizMaterielWarehouseController extends BaseController {

	@Autowired
	private BizMaterielWarehouseService bizMaterielWarehouseService;
	
	@Autowired
	private BizMaterielService bizMaterielService;
	
	@Autowired
	private BizDrawMaterielService bizDrawMaterielService;
	
	@ModelAttribute
	public BizMaterielWarehouse get(@RequestParam(required=false) String id) {
		BizMaterielWarehouse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bizMaterielWarehouseService.get(id);
		}
		if (entity == null){
			entity = new BizMaterielWarehouse();
		}
		return entity;
	}
	
	@RequiresPermissions("biz:bizMaterielWarehouse:view")
	@RequestMapping(value = {"list", ""})
	public String list(BizMaterielWarehouse bizMaterielWarehouse, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		Page<BizMaterielWarehouse> page = bizMaterielWarehouseService.findPage(new Page<BizMaterielWarehouse>(request, response), bizMaterielWarehouse); 
		model.addAttribute("page", page);
		return "modules/biz/bizMaterielWarehouseList";
	}

	@RequiresPermissions("biz:bizMaterielWarehouse:view")
	@RequestMapping(value = "form")
	public String form(BizMaterielWarehouse bizMaterielWarehouse, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		model.addAttribute("bizMaterielWarehouse", bizMaterielWarehouse);
		return "modules/biz/bizMaterielWarehouseForm";
	}
	
	@RequiresPermissions("biz:bizMaterielWarehouse:view")
	@RequestMapping(value = "toDrawMateriel")
	public String toDrawMateriel(BizMaterielWarehouse bizMaterielWarehouse, Model model) {
		List<BizMateriel> materielList = bizMaterielService.findList(new BizMateriel());
		model.addAttribute("materielList", materielList);
		model.addAttribute("bizMaterielWarehouse", bizMaterielWarehouse);
		return "modules/biz/bizDrawMaterielForm";
	}
	
	@ResponseBody
	@RequestMapping(value = "doDrawMateriel")
	public Map<String,String> doDrawMateriel(BizMaterielWarehouse bizMaterielWarehouse, Model model, RedirectAttributes redirectAttributes) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "1");
		try {
			BizDrawMateriel bizDrawMateriel = new BizDrawMateriel();
			bizDrawMateriel.setBizMateriel(bizMaterielWarehouse.getBizMateriel());
			bizDrawMateriel.setDrawBatch(SerialNumber.createSerial());
			bizDrawMateriel.setDrawExplain(bizMaterielWarehouse.getRemarks());
			bizDrawMateriel.setDrawNumber(bizMaterielWarehouse.getInNumber());
			bizDrawMateriel.setDrawTime(new Date());
			bizDrawMateriel.setStaffId(UserUtils.getUser().getId());
			bizDrawMaterielService.save(bizDrawMateriel);
			
			System.out.println(bizMaterielWarehouse.getId());
			
			BizMaterielWarehouse tt = bizMaterielWarehouseService.get(bizMaterielWarehouse.getId());
			
			int surplusNum = Integer.parseInt(tt.getInNumber()) - Integer.parseInt(bizDrawMateriel.getDrawNumber());
			tt.setInNumber(surplusNum + "");
			bizMaterielWarehouseService.save(tt);
			
			map.put("result", "0");
		} catch (Exception e) {
			e.printStackTrace();
			map.put("errorMsg", e.toString());
		}
		return map;
	}

	@RequiresPermissions("biz:bizMaterielWarehouse:edit")
	@RequestMapping(value = "save")
	public String save(BizMaterielWarehouse bizMaterielWarehouse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bizMaterielWarehouse)){
			return form(bizMaterielWarehouse, model);
		}
		
		//生成批次号
		String batchNum = SerialNumber.createSerial();
		bizMaterielWarehouse.setInBatch(batchNum);
		bizMaterielWarehouse.setStaffId(UserUtils.getUser().getId());
		bizMaterielWarehouseService.save(bizMaterielWarehouse);
		addMessage(redirectAttributes, "保存物料入库成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizMaterielWarehouse/?repage";
	}
	
	@RequiresPermissions("biz:bizMaterielWarehouse:edit")
	@RequestMapping(value = "delete")
	public String delete(BizMaterielWarehouse bizMaterielWarehouse, RedirectAttributes redirectAttributes) {
		bizMaterielWarehouseService.delete(bizMaterielWarehouse);
		addMessage(redirectAttributes, "删除物料入库成功");
		return "redirect:"+Global.getAdminPath()+"/biz/bizMaterielWarehouse/?repage";
	}

}