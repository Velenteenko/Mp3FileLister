package com.vde.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.vde.dao.Mp3DAO;
import com.vde.object.Mp3;

@Component("sqliteDao")
public class SQLiteDAOImpl implements Mp3DAO {

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void insert(List<Mp3> mp3s) {

		for (Mp3 mp3 : mp3s) {
			insert(mp3);
		}
	}

	public int insert(Mp3 mp3) {
		String sql = "insert into mp3 (name, author) values (:name, :author)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", mp3.getName());
		param.addValue("author", mp3.getAuthor());

		KeyHolder key = new GeneratedKeyHolder();

		jdbcTemplate.update(sql, param, key);

		return key.getKey().intValue();
	}

	// help method for eject id from class
	public void delete(int id) {
		String sql = "delete from mp3 where id=:id";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("id", id);
		jdbcTemplate.update(sql, param);
	}

	public void delete(Mp3 mp3) {
	}

	public Mp3 getMp3ById(int mp3Id) {

		String sql = "select * from mp3 where id=:id";

		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("id", mp3Id);

		return jdbcTemplate.queryForObject(sql, source, new Mp3Mapper());
	}

	public List<Mp3> getMp3ListByName(String name) {

		String sql = "select * from mp3 where upper(name) like :name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", name);

		return jdbcTemplate.query(sql, params, new Mp3Mapper());

	}

	public List<Mp3> getMp3ListByAuthor(String author) {
		String sql = "select * from mp3 where upper(author) like :name";

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", author);

		return jdbcTemplate.query(sql, params, new Mp3Mapper());
	}

	private static final class Mp3Mapper implements RowMapper<Mp3> {

		public Mp3 mapRow(ResultSet rs, int arg1) throws SQLException {
			Mp3 mp3 = new Mp3();
			mp3.setId(rs.getInt("id"));
			mp3.setAuthor(rs.getString("author"));
			mp3.setName(rs.getString("name"));
			return mp3;
		}

	}

	public Map<String, Integer> getStatistic() {
		String sql = "select author, count(*) as count from mp3 group by author";

		return jdbcTemplate.query(sql, new MapSqlParameterSource(), new ResultSetExtractor<Map<String, Integer>>() {

			public Map<String, Integer> extractData(ResultSet rs) throws SQLException {
				Map<String, Integer> map = new TreeMap<String, Integer>();
				while (rs.next()) {
					String author = rs.getString("author");
					int count = rs.getInt("count");
					map.put(author, count);
				}
				return map;
			};

		});

	}

	public int getMp3Count() {
		String sql = "select count(*) from mp3";

		return jdbcTemplate.getJdbcOperations().queryForInt(sql);
	}
}
