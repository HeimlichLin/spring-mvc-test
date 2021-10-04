package idv.heimlich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.heimlich.dao.OrgDao;
import idv.heimlich.domain.OrgDo;

@Service
public class OrgService {

	@Autowired
	private OrgDao orgDao;
	
	public List<OrgDo> getOrgList() {
		List<OrgDo> orgList = this.orgDao.getAllOrgs();
		return orgList;
	}
	
}
