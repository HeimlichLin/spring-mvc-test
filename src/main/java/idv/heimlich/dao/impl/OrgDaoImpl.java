package idv.heimlich.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import idv.heimlich.dao.OrgDao;
import idv.heimlich.domain.OrgDo;

@Repository("orgDao")
public class OrgDaoImpl extends GeneralDAOImpl<OrgDo> implements OrgDao {

	private JdbcTemplate jdbcTemplate;

	protected static RowMapper<OrgDo> CONVERTER = new RowMapper<OrgDo>() {

		@Override
		public OrgDo mapRow(ResultSet rs, int rowNum) throws SQLException {
			final OrgDo orgDo = new OrgDo();
			orgDo.setId(rs.getInt("id"));
			orgDo.setName(rs.getString("name"));
			orgDo.setYear(rs.getInt("year"));
			orgDo.setCode(rs.getString("code"));
			return orgDo;
		}

	};

	public RowMapper<OrgDo> getConverter() {
		return CONVERTER;
	}

	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	@Override
	public boolean create(OrgDo orgDo) {
		final String sql = "INSERT INTO org (id, name, year, code) "
				+ " VALUES (nvl((select max(ID) from org), 0) + 1, ?, ?, ?) ";
		final Object[] args = new Object[] { orgDo.getName(), orgDo.getYear(), orgDo.getCode() };
		return this.jdbcTemplate.update(sql, args) == 1;
	}

	@Override
	public OrgDo getOrg(Integer id) {
		final String sql = "SELECT ID, NAME, YEAR, CODE FROM org WHERE id = ? ";
		final Object[] args = new Object[] { id };
		final OrgDo orgDo = this.jdbcTemplate.queryForObject(sql, args, CONVERTER);
		return orgDo;
	}

	@Override
	public List<OrgDo> getAllOrgs() {
		final String sql = "SELECT * FROM org ";
//		List<OrgDo> orgList = jdbcTemplate.query(sqlQuery, new OrgRowMapper());
		final List<OrgDo> orgList = this.jdbcTemplate.query(sql, CONVERTER);
		return orgList;
	}

	@Override
	public boolean delete(OrgDo orgDo) {
		final String sql = "DELETE FROM org WHERE id = ? ";
		final Object[] args = new Object[] { orgDo.getId() };
		return this.jdbcTemplate.update(sql, args) == 1;
	}

	@Override
	public boolean update(OrgDo orgDo) {
		final String sql = "UPDATE org SET code = ? WHERE id = ? ";
		final Object[] args = new Object[] { orgDo.getCode(), orgDo.getId() };
		return this.jdbcTemplate.update(sql, args) == 1;
	}

	/**
	 * Delete 逐筆刪 Truncate table 全刪，TABLE會留 Drop table 全刪，TABLE不留
	 */
	@Override
	public void cleanup() {
		final String sql = "TRUNCATE TABLE org ";
		this.jdbcTemplate.execute(sql);
	}

}
