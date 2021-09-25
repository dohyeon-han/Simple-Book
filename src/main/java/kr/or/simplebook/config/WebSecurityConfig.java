package kr.or.simplebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity // spring security 활성화
@Configuration // 설정 파일, bean 등록
@PropertySource(value= {"classpath:application.properties"})
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
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

		// 로그인 설정
		/// login에서 로그인
		// 로그인 후 /로 이동
		// 모든 유저의 접근 허용
		http.formLogin()/*.loginPage("/login") 사용자 정의 로그인 페이지*/.defaultSuccessUrl("/").permitAll();

		// 로그아웃 설정
		/// logout으로 로그아웃
		// 로그아웃 성공 시 /login으로 이동,
		// 세션 제거
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.invalidateHttpSession(true);

		// 권한없는 사용자 /denied로 이동
		http.exceptionHandling().accessDeniedPage("/denied");
	}
}
