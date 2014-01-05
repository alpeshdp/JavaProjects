package com.app.migration.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SchemaDao implements InitializingBean {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	public void afterPropertiesSet() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String findLatestLiquibaseTagName() {
		
		String tagName = null;
		try {
			tagName = jdbcTemplate.queryForObject("SELECT tag FROM databasechangelog ORDER BY DATEEXECUTED desc LIMIT 1", String.class);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return tagName;
	}
	
	
}
