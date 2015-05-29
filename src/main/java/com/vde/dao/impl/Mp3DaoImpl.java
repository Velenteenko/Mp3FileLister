package com.vde.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vde.dao.Mp3DaoNew;
import com.vde.object.Artist;
import com.vde.object.Mp3;

@Component("sqliteDaoNew")
public class Mp3DaoImpl implements Mp3DaoNew {
	
	private static final String mp3Table = "mp3";
	private static final String mp3Viev = "mp3_view";

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

	@Transactional
	public int insert(Mp3 mp3) {
		// MapSqlParameterSource param = new MapSqlParameterSource();
		// param.addValue("name", mp3.getName());
		// param.addValue("author", mp3.getAuthor());

//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("name", mp3.getName());
//		param.put("author", mp3.getAuthor());
//
//		return insertMp3.execute(param);
		
		String sqlInsAuthor = "insert into artist (name) values (:name)";
		Artist artist = mp3.getAuthor();
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", artist.getName() );
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplateNamed.update(sqlInsAuthor, param, keyHolder);
		
		int artistKey = keyHolder.getKey().intValue();
		
		String sqlInsTrack = "insert into track (trackartist, name) values (:trArt, :name)";
		param = new MapSqlParameterSource();
		param.addValue("trArt", artistKey);
		param.addValue("name", mp3.getName());
		
		return jdbcTemplateNamed.update(sqlInsTrack, param);
		
		
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
