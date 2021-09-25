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

@EnableWebSecurity // spring security Ȱ��ȭ
@Configuration // ���� ����, bean ���
@PropertySource(value= {"classpath:application.properties"})
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
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

		// �α��� ����
		/// login���� �α���
		// �α��� �� /�� �̵�
		// ��� ������ ���� ���
		http.formLogin()/*.loginPage("/login") ����� ���� �α��� ������*/.defaultSuccessUrl("/").permitAll();

		// �α׾ƿ� ����
		/// logout���� �α׾ƿ�
		// �α׾ƿ� ���� �� /login���� �̵�,
		// ���� ����
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.invalidateHttpSession(true);

		// ���Ѿ��� ����� /denied�� �̵�
		http.exceptionHandling().accessDeniedPage("/denied");
	}
}
