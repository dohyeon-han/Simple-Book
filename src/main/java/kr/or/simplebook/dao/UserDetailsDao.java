package kr.or.simplebook.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import kr.or.simplebook.entity.CustomUserDetails;

public class UserDetailsDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CustomUserDetails> userMapper = BeanPropertyRowMapper.newInstance(CustomUserDetails.class);
	
	public UserDetailsDao(DataSource datasource) {
		this.jdbc = new NamedParameterJdbcTemplate(datasource);
	}
	
	public CustomUserDetails selectUser(String username) {
		String sql = "SELECT username, password, authority, enable"
				+ "FROM user WHERE username = :username";
		return jdbc.queryForObject(sql, Collections.singletonMap("username", username), userMapper);
	}
}
