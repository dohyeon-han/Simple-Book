package kr.or.simplebook.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.simplebook.dao.UserDetailsDao;
import kr.or.simplebook.entity.CustomUserDetails;
import kr.or.simplebook.service.CustomUserDetailsService;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {
	@Autowired
	UserDetailsDao userDetailsDao;
	
	@Autowired
	PasswordEncoder passwordEncode; 

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = userDetailsDao.selectUser(username);
		return user;
	}

	@Transactional
	public CustomUserDetails joinUser(String username, String password) {
		CustomUserDetails user = null;
		if (loadUserByUsername(username) == null) {
			user = userDetailsDao.insertUser(username, passwordEncode.encode(password));
			System.out.println("insert success");
			user.setUsername(username);
			user.setPassword(password);
			user.setAuthorities("USER");
			return user;
		} else {
			System.out.println("user exist");
			return null;
		}
	}
}
