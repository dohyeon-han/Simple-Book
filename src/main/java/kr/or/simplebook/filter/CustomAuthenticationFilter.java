package kr.or.simplebook.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.simplebook.entity.UserInfo;
import lombok.extern.log4j.Log4j2;

// 요청이 오면
// 아이디와 비밀번호를 기반으로 UserPasswordAuthenticationToken을 발급
// AuthenticationManager로 인증 객체 전달

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public CustomAuthenticationFilter(final AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response)
			throws AuthenticationException {
		final UsernamePasswordAuthenticationToken authRequest;
		try {
			final UserInfo user = new ObjectMapper().readValue(request.getInputStream(), UserInfo.class);
			authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		} catch (IOException exception) {
			throw new RuntimeException();
		}
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
