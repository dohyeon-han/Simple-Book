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

@EnableWebSecurity // spring security Ȱ��ȭ
@Configuration // ���� ����, bean ���
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
	public void configure(WebSecurity web) throws Exception {// �������� ����
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//
		http.authorizeRequests().anyRequest().permitAll().and()//��ū�� Ȱ���� ��� ��û ���
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//Session ��� ����
		.and().formLogin().disable()
		//UsernamePasswordAuthenticationFilter ���� ���� ����
		.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class);

		
		/*
		 * // �α��� ���� // login���� �α��� // id,pw�� �Ķ���ͷ� ���� // /loginprocess�� �̵�(post) // ���
		 * ������ ���� ��� http.formLogin() .loginPage("/login")
		 * .defaultSuccessUrl("/").permitAll()
		 * .usernameParameter("userEmail").passwordParameter("userPw").and();
		 * 
		 * // �α׾ƿ� ���� /// logout���� �α׾ƿ� // �α׾ƿ� ���� �� /login���� �̵�, // ���� ����
		 * http.logout().logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
		 * .invalidateHttpSession(true).deleteCookies();
		 * 
		 * // ���Ѿ��� ����� /denied�� �̵� http.exceptionHandling().accessDeniedPage("/denied");
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
