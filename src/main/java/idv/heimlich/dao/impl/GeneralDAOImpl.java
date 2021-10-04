package idv.heimlich.dao.impl;

import javax.sql.DataSource;

import idv.heimlich.dao.GeneralDAO;

public abstract class GeneralDAOImpl<PO> implements GeneralDAO<PO> {

	@Override
	public abstract void setDataSource(DataSource dataSource);

	@Override
	public abstract boolean create(PO orgDo);

	@Override
	public abstract boolean delete(PO orgDo);

	@Override
	public abstract boolean update(PO orgDo);

	@Override
	public abstract void cleanup();

}
