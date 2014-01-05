package com.app.schema.manager;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import liquibase.exception.CommandLineParsingException;

import com.app.migration.AppProperties;
import com.app.migration.LiquibaseUpdateStep;
import com.app.migration.MigrationStep;
import com.app.migration.MySqlScemaExportStep;
import com.app.migration.MySqlScemaImportStep;
import com.app.migration.dao.SchemaDao;

public class SchemaManager {

	public static Pattern pattern =  Pattern.compile("r_(\\d+)");
	/**
	 * @param args
	 * @throws CommandLineParsingException
	 * @throws IOException
	 */
	public static void main(String[] args) throws CommandLineParsingException, IOException {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:DatabaseChangeManahementApp.xml");
		AppProperties properties = ctx.getBean(AppProperties.class);
		System.out.println(properties);
		
		//TODO
		//Step-0.1: Query and find all database schemas that needs to be Migrated.
		
		//Step-0.2: Query and find out last applied tag, to which change set can be rolled back.
		SchemaDao schemaDao = ctx.getBean(SchemaDao.class);
		String latestLiquibaseTagName = schemaDao.findLatestLiquibaseTagName();
		String nextLiquibaseTagName = getNextLiquibaseTagName(latestLiquibaseTagName);

		System.out.println(latestLiquibaseTagName +" : "+nextLiquibaseTagName);
		
		//For Each such schema:
		//Step-1: Export a given MySql Database
//		MigrationStep exportStep = ctx.getBean(MySqlScemaExportStep.class);
//		exportStep.execute();
//		
//		
//		MigrationStep liquibaseUpdateStep = ctx.getBean(LiquibaseUpdateStep.class);
//		
//		//Step-2: Apply Liquibase change sets
//		boolean hasSchemaUpgrdationError = false;
//		try {
//			hasSchemaUpgrdationError = liquibaseUpdateStep.execute();
//		} catch (Exception e) {
//			hasSchemaUpgrdationError = true;
//			e.printStackTrace();
//		}
		
		//If success
		//Step-2.2: Apply new tag 
		//Step-2.3: Delete the backup file.
		
		//If failure
		//Step-2.2: Mark as an error
		//Step-2.2: Rollback to previous tag

//		MySqlScemaImportStep importStep = new MySqlScemaImportStep();
//		importStep.execute();
	}

	private static String getNextLiquibaseTagName(String latestLiquibaseTagName) {

		if(!StringUtils.hasText(latestLiquibaseTagName)) {
			return "r_1";
		} else {			
			Matcher matcher = pattern.matcher(latestLiquibaseTagName);
			int currentReleaseNumber = -2;
			if(matcher.matches()) {
				currentReleaseNumber = new Integer(matcher.group(1));
			}
			return String.format("r_%d", ++currentReleaseNumber);
		}
	}
}
