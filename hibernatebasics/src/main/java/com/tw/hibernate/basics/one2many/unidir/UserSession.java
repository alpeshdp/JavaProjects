package com.tw.hibernate.basics.one2many.unidir;

import java.text.MessageFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "SESSION")
public class UserSession {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	@ManyToMany
    @JoinTable(
            name="SESSION_LICENSE",
            joinColumns = @JoinColumn( name="SESSION_ID"),
            inverseJoinColumns = @JoinColumn( name="LICENSE_ID")
    )
	@Cascade({org.hibernate.annotations.CascadeType.ALL, org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<License> licenses;

	private UserSession() {}
	
	protected UserSession(String name, List<License> licenses) {
		this.name = name;
		this.licenses = licenses;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<License> getLicenses() {
		return licenses;
	}
	
	public void addLicense(License l) {
		getLicenses().add(l);
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("Session id:{0},  name:{1}, [licenses{2}]", id, name, licenses);
	}
	
	
	
	
}
