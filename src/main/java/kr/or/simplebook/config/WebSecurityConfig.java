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

@EnableWebSecurity // spring security Ȱ��ȭ
@Configuration // ���� ����, bean ���
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

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
		// '/login'�� ������ ��û�� ���� �䱸
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/").hasRole("USER") // USER,ADMIN��
				.antMatchers("/admin").hasRole("ADMIN") // ADMIN��
				.anyRequest().authenticated(); // �������� �������־�� ���� ����

		// �α��� ����
		// login���� �α���
		// id,pw�� �Ķ���ͷ� ����
		// /loginprocess�� �̵�(post)
		// ��� ������ ���� ���
		http.formLogin()/* .loginPage("/login") */.successForwardUrl("/").permitAll().and()
				.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

		// �α׾ƿ� ����
		/// logout���� �α׾ƿ�
		// �α׾ƿ� ���� �� /login���� �̵�,
		// ���� ����
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies();

		// ���Ѿ��� ����� /denied�� �̵�
		http.exceptionHandling().accessDeniedPage("/denied");
	}

	// filter ���� ���
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
