package idv.heimlich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import idv.heimlich.domain.OrgDo;
import idv.heimlich.service.OrgService;

@Controller
public class OrgController {

	@Autowired
	private OrgService orgService;
	
//	@RequestMapping("/jstlsql")
//	public String listOrgUsingSQLTag() {
//		return "listOrg1";
//	}
	
//	@RequestMapping("/service")
//	public String listOrgUsingService(Model model) {
//		List<OrgDo> orgs= this.orgService.getOrgList();
//		model.addAttribute("orgList", orgs);
//		return "listOrg2";
//	}
	
	@RequestMapping("/location")
	public String addLocation() {
		return "location";
	}
	
	@RequestMapping("/listOrgs")
	public String listOrgs(Model model) {
		List<OrgDo> orgs= this.orgService.getOrgList();
		model.addAttribute("orgList", orgs);
		return "listOrgs";
	}
	
	
}
