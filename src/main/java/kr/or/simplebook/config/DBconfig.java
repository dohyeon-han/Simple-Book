package kr.or.simplebook.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class DBconfig implements TransactionManagementConfigurer{
	@Autowired
	private Environment ev;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(ev.getProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(ev.getProperty("spring.datasource.url"));
		dataSource.setUsername(ev.getProperty("spring.datasource.username"));
		dataSource.setPassword(ev.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}
