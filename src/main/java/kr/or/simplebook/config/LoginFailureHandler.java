package kr.or.simplebook.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		if(exception instanceof BadCredentialsException ||
				exception instanceof AuthenticationServiceException ||
				exception instanceof UsernameNotFoundException) {
			session.setAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀립니다.");
			
		} else if(exception instanceof LockedException) {
			session.setAttribute("loginFailMsg", "잠긴 계정입니다.");
			
		} else if(exception instanceof DisabledException) {
			session.setAttribute("loginFailMsg", "비활성화된 계정입니다.");
			
		} else if(exception instanceof AccountExpiredException) {
			session.setAttribute("loginFailMsg", "만료된 계정입니다.");
			
		} else if(exception instanceof CredentialsExpiredException) {
			session.setAttribute("loginFailMsg", "비밀번호가 만료되었습니다.");
		}
		
		// 로그인 페이지로 포워딩
		response.sendRedirect("/login?error=true");
	}
}