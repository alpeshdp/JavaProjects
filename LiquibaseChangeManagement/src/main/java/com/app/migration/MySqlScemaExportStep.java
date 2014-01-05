package com.app.migration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value="MySqlScemaExportStep")
public class MySqlScemaExportStep implements MigrationStep {

	@Autowired
	AppProperties properties;
	
	public boolean execute() {

		try {
//			Process p = Runtime.getRuntime().exec("mysqldump -uroot -ppassword plms4 -r D:\\test\\mysqldumps\\plms4.sql");
			String commandFormat = "C:\\Program Files\\MySQL\\MySQL Server 5.6\\bin\\mysqldump.exe -u%s -p%s %s -r D:\\test\\mysqldumps\\%s.dmp";
			String command = String.format(commandFormat, properties.getDatabaseUserName(), properties.getDatabasePassword(), "tree_1", "tree_1_backup");
			System.out.println(command);
			
			Process p = Runtime.getRuntime().exec(command);
//			Process p = Runtime.getRuntime().exec(new String[]{"C:\\Program Files\\MySql\\MySql Server 5.6\\bin\\mysqldump", "-u", "root", "-p", "password", "plms4", "> D:\\test\\mysqldumps\\plms4.dmp"});
			p.waitFor();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			String line = reader.readLine();
			while (line != null) {
				line += reader.readLine();
				System.out.println(line);
			}
			int exitValue = p.exitValue();
			if(exitValue!=0) {
				System.out.println(String.format("Some error occured. Statscode:[%d], Message:[%s]", exitValue, "Failure."));
				throw new RuntimeException(String.format("Error in Step - %s.", getClass().getName()));
			} else {
				System.out.println(String.format("Export completed. Statscode:[%d], Message:[%s]", exitValue, "Success."));
			}

		} catch (Exception e) {
			throw new RuntimeException(String.format("Error in Step {%s}",
					getClass().getName()), e);
		}
		return true;
	}

}
