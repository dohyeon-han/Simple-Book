package kr.or.simplebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.or.simplebook.authenticationfilter.CustomAuthenticationFilter;
import kr.or.simplebook.handler.CustomLoginSuccessHandler;

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
		// '/login'을 제외한 요청은 인증 요구
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/").hasRole("USER") // USER,ADMIN만
				.antMatchers("/admin").hasRole("ADMIN") // ADMIN만
				.anyRequest().authenticated(); // 나머지는 권한이있어야 접근 가능

		// 로그인 설정
		// login에서 로그인
		// id,pw를 파라미터로 설정
		// /loginprocess로 이동(post)
		// 모든 유저의 접근 허용
		http.formLogin()/* .loginPage("/login") */.successForwardUrl("/").permitAll().and()
				.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// 로그아웃 설정
		/// logout으로 로그아웃
		// 로그아웃 성공 시 /login으로 이동,
		// 세션 제거
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies();

		// 권한없는 사용자 /denied로 이동
		http.exceptionHandling().accessDeniedPage("/denied");
	}

	// filter 전에 등록
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}

	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

}
