package kr.or.simplebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.or.simplebook.filter.JwtFilter;
import kr.or.simplebook.handler.CustomLoginSuccessHandler;
import kr.or.simplebook.service.CustomUserDetailsService;

@EnableWebSecurity // spring security 활성화
@Configuration // 설정 파일, bean 등록
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtFilter jwtFilter;

	
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
		//
		http.authorizeRequests().anyRequest().permitAll().and()//토큰을 활용한 모든 요청 허용
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Session 사용 안함
		.and().formLogin().disable()
		//UsernamePasswordAuthenticationFilter 보다 먼저 실행
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

		
		/*
		 * // 로그인 설정 // login에서 로그인 // id,pw를 파라미터로 설정 // /loginprocess로 이동(post) // 모든
		 * 유저의 접근 허용 http.formLogin() .loginPage("/login")
		 * .defaultSuccessUrl("/").permitAll()
		 * .usernameParameter("userEmail").passwordParameter("userPw").and();
		 * 
		 * // 로그아웃 설정 /// logout으로 로그아웃 // 로그아웃 성공 시 /login으로 이동, // 세션 제거
		 * http.logout().logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
		 * .invalidateHttpSession(true).deleteCookies();
		 * 
		 * // 권한없는 사용자 /denied로 이동 http.exceptionHandling().accessDeniedPage("/denied");
		 */
	}

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}

}
