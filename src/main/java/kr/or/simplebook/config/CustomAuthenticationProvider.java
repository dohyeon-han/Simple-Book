package kr.or.simplebook.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import kr.or.simplebook.service.CustomUserDatailService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomUserDatailService customUserDatailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// DB�� �����Ϳ� ���� �� �� Authentication ���� ������ spring security�� ����
		//�Է��� id,pw
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();

		if (username.equals("fail")) {
			System.out.println("username fail");
			throw new UsernameNotFoundException(username);
		}
		
		UserDetails user = customUserDatailService.getUserById(username);

		//pw Ȯ��
		if (!matchPassword(password, user.getPassword())) {
			System.out.println("password not match");
			throw new BadCredentialsException(password);
		}
		@SuppressWarnings("unchecked")
		List<GrantedAuthority> roles = (List<GrantedAuthority>) user.getAuthorities();
		
		UsernamePasswordAuthenticationToken result =
				new UsernamePasswordAuthenticationToken(username, password, roles);
		
		result.setDetails(user);
		
		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	private boolean matchPassword(String loginPwd, String password) {
		BCryptPasswordEncoder secure = new BCryptPasswordEncoder();
		return secure.matches(loginPwd, password);
	}
}
