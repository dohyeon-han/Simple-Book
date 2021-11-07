package kr.or.simplebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity // spring security 활성화
@Configuration // 설정 파일, bean 등록
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {// 인증에서 제외
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //
				.authorizeRequests().antMatchers("/login*").permitAll()// login은 모든 사용자 접근 가능
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login") // login.jsp
				.loginProcessingUrl("/doLogin") // post url
				.defaultSuccessUrl("/") // 성공 후 url
				.usernameParameter("id").passwordParameter("pw") // id, pw에 대한 파라미터
				.successHandler(new LoginSuccessHandler()) // id를 세션에 저장
				.failureHandler(new LoginFailureHandler())
				.permitAll()
			.and()
				.logout().logoutUrl("/doLogout") // logout 요청 url
				.logoutSuccessUrl("/login") // logout 후 url
				.invalidateHttpSession(true) // 세션 삭제
				.deleteCookies("true") // 쿠키 삭제
			.and()
				.exceptionHandling().accessDeniedPage("/login");// 접근 권한이 없을 때 접근 시 이동
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
				.and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER").and()
				.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}
}
