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
		http.csrf().disable() //
				.authorizeRequests().antMatchers("/login*").permitAll()// login�� ��� ����� ���� ����
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login") // login.jsp
				.loginProcessingUrl("/doLogin") // post url
				.defaultSuccessUrl("/") // ���� �� url
				.usernameParameter("id").passwordParameter("pw") // id, pw�� ���� �Ķ����
				.successHandler(new LoginSuccessHandler()) // id�� ���ǿ� ����
				.failureHandler(new LoginFailureHandler())
				.permitAll()
			.and()
				.logout().logoutUrl("/doLogout") // logout ��û url
				.logoutSuccessUrl("/login") // logout �� url
				.invalidateHttpSession(true) // ���� ����
				.deleteCookies("true") // ��Ű ����
			.and()
				.exceptionHandling().accessDeniedPage("/login");// ���� ������ ���� �� ���� �� �̵�
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
				.and().withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER").and()
				.withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
	}
}
