package kr.or.simplebook.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.simplebook.entity.CustomUserDetails;

@Repository
public class UserDetailsDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CustomUserDetails> userMapper = BeanPropertyRowMapper.newInstance(CustomUserDetails.class);
	
	public UserDetailsDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public CustomUserDetails selectUser(String username) {
		String sql = "SELECT username, password, enable "
				+ "FROM users WHERE username = :username";
		try {
			return jdbc.queryForObject(sql, Collections.singletonMap("username", username), userMapper);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
}
