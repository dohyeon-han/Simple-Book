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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.or.simplebook.successhandler.LoginSuccessHandler;

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
		http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

		// �α��� ����
		// login���� �α���
		// id,pw�� �Ķ���ͷ� ����
		// /loginprocess�� �̵�(post)
		// ��� ������ ���� ���
		http.formLogin()/*.loginPage("/login")*/.successHandler(new LoginSuccessHandler()).permitAll();
		

		// �α׾ƿ� ����
		/// logout���� �α׾ƿ�
		// �α׾ƿ� ���� �� /login���� �̵�,
		// ���� ����
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
				.invalidateHttpSession(true).deleteCookies();

		// ���Ѿ��� ����� /denied�� �̵�
		http.exceptionHandling().accessDeniedPage("/denied");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
        .withUser("admin")
    	.password(passwordEncoder().encode("1234")).roles("admin");//admin,1234�� admin���� ���� test��
	}

}
