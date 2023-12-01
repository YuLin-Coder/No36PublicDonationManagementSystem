/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.online.itg.modules.cms.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.online.itg.common.persistence.Page;
import com.online.itg.common.web.BaseController;
import com.online.itg.modules.cms.entity.Guestbook;
import com.online.itg.modules.cms.entity.Site;
import com.online.itg.modules.cms.service.GuestbookService;
import com.online.itg.modules.cms.utils.CmsUtils;

/**
 * 留言板Controller
 * @author ThinkGem
 * @version 2013-3-15
 */
@Controller
@RequestMapping(value = "${adminPath}/guestbook")
public class FrontGuestbookController extends BaseController{
	
	@Autowired
	private GuestbookService guestbookService;

	/**
	 * 留言板列表
	 */
	@RequestMapping(value = "form", method=RequestMethod.GET)
	public String guestbookForm(@RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="10") Integer pageSize, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		
		return "modules/cms/front/themes/"+site.getTheme()+"/frontGuestbookForm";
	}
	
	/**
	 * 我要留言
	 */
	@RequestMapping(value = "showList", method=RequestMethod.GET)
	public String guestbookList(@RequestParam(required=false, defaultValue="1") Integer pageNo,
			@RequestParam(required=false, defaultValue="10") Integer pageSize, Model model) {
		Site site = CmsUtils.getSite(Site.defaultSiteId());
		model.addAttribute("site", site);
		
		Page<Guestbook> page = new Page<Guestbook>(pageNo, pageSize);
		Guestbook guestbook = new Guestbook();
		guestbook.setDelFlag(Guestbook.DEL_FLAG_NORMAL);
		page = guestbookService.findPage(page, guestbook);
		model.addAttribute("page", page);
		return "modules/cms/front/themes/"+site.getTheme()+"/frontGuestbookList";
	}
	
	/**
	 * 留言板-保存留言信息
	 */
	@ResponseBody
	@RequestMapping(value = "save")
	public Map<String,String> guestbookSave(Guestbook guestbook, String validateCode, HttpServletRequest request, HttpServletResponse response) {
		Map<String,String> map = new HashMap<String,String>();
//		if (StringUtils.isNotBlank(validateCode)){
//			if (ValidateCodeServlet.validate(request, validateCode)){
				guestbook.setIp(request.getRemoteAddr());
				guestbook.setCreateDate(new Date());
				guestbook.setDelFlag(Guestbook.DEL_FLAG_AUDIT);
				guestbookService.save(guestbook);
				map.put("result", "1");
//			}else{
//				addMessage(redirectAttributes, "验证码不正确。");
//				map.put("result", "0");
//			}
//		}else{
//			addMessage(redirectAttributes, "验证码不能为空。");
//				map.put("result", "0");
//		}
		return map;
	}
	
}
