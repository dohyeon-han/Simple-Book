package kr.or.simplebook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final�� ���� �ʵ忡 ���� �����ڸ� �����Ѵ�.
@EnableWebSecurity // 1
@Configuration 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // 2

  private final UserDetailsService userService = null;

  @Override
  public void configure(WebSecurity web) { // 4
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception { // 5
    http
          .authorizeRequests() // 6
            .antMatchers("/login", "/signup", "/user").permitAll() // ������ ���� ���
            .antMatchers("/").hasRole("USER") // USER, ADMIN�� ���� ����
            .antMatchers("/admin").hasRole("ADMIN") // ADMIN�� ���� ����
            .anyRequest().authenticated() // ������ ��û���� ������ ������ ��� ���� ������ �־�� ���� ����
        .and() 
          .formLogin() // 7
            .loginPage("/login") // �α��� ������ ��ũ
            .defaultSuccessUrl("/") // �α��� ���� �� �����̷�Ʈ �ּ�
        .and()
          .logout() // 8
            .logoutSuccessUrl("/login") // �α׾ƿ� ������ �����̷�Ʈ �ּ�
	    .invalidateHttpSession(true) // ���� ������
    ;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
    auth.userDetailsService(userService)
    	// �ش� ����(userService)������ UserDetailsService�� implements�ؼ� 
        // loadUserByUsername() �����ؾ��� (���� ����)
    	.passwordEncoder(new BCryptPasswordEncoder()); 
   }
}
