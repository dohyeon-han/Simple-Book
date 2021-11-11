package kr.or.simplebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.simplebook.dao.UserDetailsDao;
import kr.or.simplebook.entity.CustomUserDetails;

@Service
public class CustomUserDatailService {
	@Autowired
	private UserDetailsDao userDetailsDao;
	
	public CustomUserDetails getUserById(String id) throws AuthenticationServiceException {
		CustomUserDetails user = userDetailsDao.selectUser(id);
        if(user==null) {
            throw new UsernameNotFoundException(id);
        }
        return user;
	}
}
