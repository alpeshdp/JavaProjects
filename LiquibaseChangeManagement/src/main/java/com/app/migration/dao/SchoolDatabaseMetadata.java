package com.app.migration.dao;

public class SchoolDatabaseMetadata {

	private String schemaName;
	private String userName;
	private String passwoord;
	
	public SchoolDatabaseMetadata(String schemaName, String userName, String passwoord) {
		super();
		this.schemaName = schemaName;
		this.userName = userName;
		this.passwoord = passwoord;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPasswoord() {
		return passwoord;
	}
	
}
