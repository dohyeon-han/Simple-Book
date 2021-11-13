package kr.or.simplebook.service;

import kr.or.simplebook.entity.CustomUserDetails;

public interface CustomUserDetailsService {
	public CustomUserDetails loadUserByUsername(String username);
	public CustomUserDetails joinUser(String username, String password);
}