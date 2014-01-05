package com.app.migration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MySqlScemaImportStep implements MigrationStep {


	public boolean execute() {

		try {
			 String dbUserName = "root";
			String dbPassword = "password";
			String dbName = "tree_1";
			String source = "D:\\test\\mysqldumps\\tree_1.dmp";
			String[] executeCmd = new String[]{"mysql", "--user=" + dbUserName, "--password=" + dbPassword , dbName ,"-e", "source "+source };;
			
			Process p = Runtime.getRuntime().exec(executeCmd);
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
				System.out.println(String.format("Some error occured. Statscode:{%d}, Message:{%s}", exitValue, "Failure."));
				throw new RuntimeException(String.format("Error in Step {%s}.", getClass().getName()));
			} else {
				System.out.println(String.format("Import completed. Statscode:{%d}, Message:{%s}", exitValue, "Success."));
			}

		} catch (Exception e) {
			throw new RuntimeException(String.format("Error in Step {%s}",
					getClass().getName()), e);
		}
		return true;
	}

}
