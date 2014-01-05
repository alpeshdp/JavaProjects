package com.app.migration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:LiquibaseApp.properties")
public class AppProperties {
	
	@Value("${changeLogFile}")
	private String changeLogFile;
	
	@Value("${db.driver}")
	private String databaseDriver;

	@Value("${db.url}")
	private String databaseUrl;
	
	@Value("${db.username}")
	private String databaseUserName;
	
	@Value("${db.password}")
	private String databasePassword;
	
	public String getChangeLogFileName() {
		return changeLogFile;
	}

	public String getDatabaseDriver() {
		return databaseDriver;
	}

	public String getDatabaseUrl() {
		return databaseUrl;
	}

	public String getDatabaseUserName() {
		return databaseUserName;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public String toString() {
		return "AppProperties [changeLogFile=" + changeLogFile
				+ ", databaseDriver=" + databaseDriver + ", databaseUrl="
				+ databaseUrl + ", databaseUserName=" + databaseUserName
				+ ", databasePassword=" + databasePassword + "]";
	}
	
	
}