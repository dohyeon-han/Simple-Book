package kr.or.simplebook.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.extern.log4j.Log4j2;

// 처음 요청이 오면 AuthenticationFilter를 지나
// 아이디와 비밀번호를 기반으로 UserPasswordAuthenticationToken을 발급
// AuthenticationManager로 인증 객체 전달
@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {//생성자
		super.setAuthenticationManager(authenticationManager);
	}

	// token 발급
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				request.getParameter("userEmail"), request.getParameter("userPw"));
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
