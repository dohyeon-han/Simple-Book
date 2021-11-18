package kr.or.simplebook.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.simplebook.entity.CustomUserDetails;
import static kr.or.simplebook.dao.Sql.*;

@Repository
public class UserDetailsDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CustomUserDetails> userMapper = BeanPropertyRowMapper.newInstance(CustomUserDetails.class);
	
	public UserDetailsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public CustomUserDetails selectUser(String username) {
		try {
			return jdbc.queryForObject(SELECT_USER, Collections.singletonMap("username", username), userMapper);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	public int insertUser(String username, String password) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username",username);
		param.put("password",password);
		param.put("enable",true);
		return jdbc.update(INSERT_USER, param);
	}
	
	public int insertAuth(String username, String auth) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("username",username);
		param.put("authority","USER");
		return jdbc.update(INSERT_AUTH, param);
	}
}
