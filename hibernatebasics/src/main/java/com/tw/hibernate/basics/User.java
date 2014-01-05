package com.tw.hibernate.basics;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.io.Serializable;

@Entity
public class User {

//	@EmbeddedId
//	@AttributeOverride(name = "firstName", column = @Column(name = "fld_firstname"))
//	UserId id;
	
	@Id
	@GeneratedValue
	Long userId;

	@Column
	Integer age;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dateAssigned;	
	
	@Column
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTimeZoneAsString")
	DateTimeZone dateTimeZone;
	
	public DateTimeZone getDateTimeZone() {
		return dateTimeZone;
	}

	public void setDateTimeZone(DateTimeZone dateTimeZone) {
		this.dateTimeZone = dateTimeZone;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	
	
//	public UserId getId() {
//		return id;
//	}
//
//	public void setId(UserId id) {
//		this.id = id;
//	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserId createUid(String f, String l) {
		UserId userId = new UserId();
		userId.firstName=f;
		userId.lastName=l;
		return null;
	}

}

@Embeddable
class UserId implements Serializable {
	String firstName;
	String lastName;
}
