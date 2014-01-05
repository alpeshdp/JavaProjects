package com.tw.hibernate.basics.many2many.bidir;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer {

    private int id;
    private String name;
    private List<Employee> employees = new ArrayList<Employee>();

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(
            targetEntity = com.tw.hibernate.basics.many2many.bidir.Employee.class,
            cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name = "EMPLOYER_EMPLOYEE",
            joinColumns = @JoinColumn(name = "EMPLOYER_ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID")
    )
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
