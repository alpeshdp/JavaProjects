package com.app.migration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value="LiquibaseStep")
public class LiquibaseUpdateStep implements MigrationStep {
	
	@Autowired
	private AppProperties properties;

	public boolean execute() {
		String arguments[] = {
				String.format("--driver=%s", properties.getDatabaseDriver()),
				String.format("--changeLogFile=%s", properties.getChangeLogFileName()),
				String.format("--url=%s%s", properties.getDatabaseUrl(), "tree_1"),
				String.format("--username=%s", properties.getDatabaseUserName()),
				String.format("--password=%s", properties.getDatabasePassword()),
				"updateTestingRollback"
		};
		
		try {
			liquibase.integration.commandline.Main.main(arguments);
		} catch (Exception e) {
			throw new RuntimeException(String.format("Error in Step {%s}", getClass().getName()), e);
		}
		
		return true;
	}
	
}