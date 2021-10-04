package idv.heimlich.dao;

import java.util.List;

//import javax.sql.DataSource;

import idv.heimlich.domain.OrgDo;

public interface OrgDao {

//	public void setDataSource(DataSource dataSource);

//	public boolean create(OrgDo orgDo);

	public OrgDo getOrg(Integer id);

	public List<OrgDo> getAllOrgs();

//	public boolean delete(OrgDo orgDo);

//	public boolean update(OrgDo orgDo);

//	public void cleanup();

}
