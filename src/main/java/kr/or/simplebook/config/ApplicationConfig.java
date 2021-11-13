package kr.or.simplebook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

@Configuration
@ComponentScan(basePackages = {"kr.or.simplebook.dao",
		"kr.or.simplebook.config","kr.or.simplebook.serviceImpl"})
@Import({WebSecurityConfig.class, DBconfig.class, WebSecurity.class})
public class ApplicationConfig {

}