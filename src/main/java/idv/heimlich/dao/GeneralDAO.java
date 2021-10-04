package idv.heimlich.dao;

import javax.sql.DataSource;

public interface GeneralDAO<PO> {

	public void setDataSource(DataSource dataSource);

	public boolean create(PO orgDo);

	public boolean delete(PO orgDo);

	public boolean update(PO orgDo);

	public void cleanup();

}
