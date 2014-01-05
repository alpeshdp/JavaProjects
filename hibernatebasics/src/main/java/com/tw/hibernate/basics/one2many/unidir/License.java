package com.tw.hibernate.basics.one2many.unidir;

import java.text.MessageFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LICENSE")
public class License {

	@Id
	 @GeneratedValue
	private Long id;
	
	private String name;

	private Long maxUsersForLicense;
	
	License() {
	}
	
	public License(String name, Long maxUsersForLicense) {
		this.name = name;
		this.maxUsersForLicense = maxUsersForLicense;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("License (id={0}, name={1} maxUsersForLicense={2})", id, name, maxUsersForLicense);
	}
	
}
