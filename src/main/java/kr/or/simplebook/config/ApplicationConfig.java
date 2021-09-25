package kr.or.simplebook.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"kr.or.simplebook.dao", "kr.or.simplebook.service"})
@Import({WebSecurityConfig.class, DBconfig.class})
public class ApplicationConfig {

}
