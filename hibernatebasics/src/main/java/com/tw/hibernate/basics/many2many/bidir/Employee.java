package com.tw.hibernate.basics.many2many.bidir;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Employee {

    private int id;
    private String fName;
    private String lName;
    private List<Employer> employers = new ArrayList<Employer>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @ManyToMany(
            targetEntity = com.tw.hibernate.basics.many2many.bidir.Employer.class,
            mappedBy = "employees",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    public List<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employer> employers) {
        this.employers = employers;
    }

}
