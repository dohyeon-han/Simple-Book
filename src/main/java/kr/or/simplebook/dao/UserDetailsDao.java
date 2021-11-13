package kr.or.simplebook.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.simplebook.entity.CustomUserDetails;

@Repository
public class UserDetailsDao {
	private NamedParameterJdbcTemplate namedJdbc;
	private JdbcTemplate jdbcTemp;
	private RowMapper<CustomUserDetails> userMapper = BeanPropertyRowMapper.newInstance(CustomUserDetails.class);
	
	public UserDetailsDao(DataSource dataSource) {
		this.namedJdbc = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}
	
	public CustomUserDetails selectUser(String username) {
		String sql = "SELECT username, password, enable "
				+ "FROM users WHERE username = :username";
		try {
			return namedJdbc.queryForObject(sql, Collections.singletonMap("username", username), userMapper);
		}catch(EmptyResultDataAccessException e) {
			return null;
		}
		
	}
	
	public CustomUserDetails insertUser(String username, String password) {
		String insertUser = "INSERT INTO users VALUES(?, ?, ?)";
		String insertAuth = "INSERT INTO authorities VALUES(?, ?)";
		jdbcTemp.update(insertUser, username, password, true);
		jdbcTemp.update(insertAuth, username, "USER");
		CustomUserDetails user = new CustomUserDetails();
		user.setEnabled(true);
		return user;
	}
}
