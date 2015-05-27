package com.vde.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.vde.dao.Mp3DAO;
import com.vde.object.Mp3;


@Component("sqliteDao")
public class SQLiteDAOImpl implements Mp3DAO {

	private JdbcTemplate JdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.JdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void insert(List<Mp3> mp3s) {
		
		for (Mp3 mp3 : mp3s) {
			insert(mp3);
		}
	}
	
	public void insert(Mp3 mp3) {
//		String sql = "insert into mp3 (name, author) values (?,?)";
		String sql = "insert into mp3 (name, author) values (:name, :author)";
		 MapSqlParameterSource param = new MapSqlParameterSource();
		 param.addValue("name", mp3.getName());
		 param.addValue("author", mp3.getAuthor());
		 
		 JdbcTemplate.update(sql, param);
		
//		JdbcTemplate.update(sql, new Object[]{mp3.getName(), mp3.getAuthor()});

	}
	
	//help method for eject id from class
	public void delete(int id){
		String sql = "delete from mp3 where id=?";
		JdbcTemplate.update(sql, new Object[]{id});
	}

	public void delete(Mp3 mp3) {
		delete(mp3.getId());
	}

	public Mp3 getMp3ById(int mp3Id) {
		
		String sql = "select * from mp3 where id=?";
		
//		MapSqlParameterSource source = new MapSqlParameterSource();
//		source.addValue("id", mp3Id);
		
		return JdbcTemplate.queryForObject(sql, new Object[]{mp3Id}, new Mp3Mapper());
	}

	public List<Mp3> getMp3ListByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Mp3> getMp3ListByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	private static final class Mp3Mapper implements RowMapper<Mp3>{

		public Mp3 mapRow(ResultSet rs, int arg1) throws SQLException {
			Mp3 mp3 = new Mp3();
			mp3.setId(rs.getInt("id"));
			mp3.setAuthor(rs.getString("author"));
			mp3.setName(rs.getString("name"));
			return mp3;
		}
		
	}

}
