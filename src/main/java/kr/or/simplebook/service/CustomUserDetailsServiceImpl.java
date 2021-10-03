package kr.or.simplebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import kr.or.simplebook.entity.UserInfo;
import kr.or.simplebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public MyUserDetails loadUserByUsername(String email) {
		return userRepository.findByEmail(email).map(
				u -> new MyUserDetails(u, Collections.singleton(new SimpleGrantedAuthority(u.getRole().getValue()))))
				.orElseThrow(() -> new UserNotFoundException(email));
	}

}
