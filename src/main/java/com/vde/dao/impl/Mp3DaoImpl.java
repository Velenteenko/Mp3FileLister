package com.vde.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import com.vde.dao.Mp3DaoNew;
import com.vde.object.Mp3;

@Component("sqliteDaoNew")
public class Mp3DaoImpl implements Mp3DaoNew {

	private SimpleJdbcInsert insertMp3;

	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	private DataSource dataSourceCL;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSourceCL = dataSource;
		this.jdbcTemplateNamed = new NamedParameterJdbcTemplate(dataSource);
		this.insertMp3 = new SimpleJdbcInsert(dataSourceCL).withTableName("mp3");

		this.jdbcTemplateNamed = new NamedParameterJdbcTemplate(dataSource);
		// this.dataSource = dataSource;
	}

	public int insert(Mp3 mp3) {
		// MapSqlParameterSource param = new MapSqlParameterSource();
		// param.addValue("name", mp3.getName());
		// param.addValue("author", mp3.getAuthor());

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", mp3.getName());
		param.put("author", mp3.getAuthor());

		return insertMp3.execute(param);
	}

	public int insertList(List<Mp3> mp3List) {
		return 0;

	}

	public int[] insertListBatch(List<Mp3> mp3List) {

		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(mp3List.toArray());

		int[] updateCounts = jdbcTemplateNamed.batchUpdate("insert into mp3 (author,name) values (:author, :name)", batch);

		return updateCounts;
	}

	public void delete(Mp3 mp3) {
		// TODO Auto-generated method stub

	}

	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	public Mp3 getMP3ByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Mp3> getMP3ListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Mp3> getMP3ListByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getMP3Count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Map<String, Integer> getStat() {
		// TODO Auto-generated method stub
		return null;
	}

}
