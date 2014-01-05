package com.tw.hibernate.basics.one2many.unidir;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    private int serialNumber;
    private String name;
    Set<Part> parts = new HashSet<Part>();
    private int version;

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getSerialNumber() { return serialNumber; }
    void setSerialNumber(int sn) { serialNumber = sn; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    @Version
    public int getVersion() {
        return version;
    }

    void setVersion(int version) {
        this.version = version;
    }
}

/*
    @JoinTable(
        name = "PRODUCT_PARTS",
        joinColumns = @JoinColumn(name = "PRODUCT_ID"),
        inverseJoinColumns = @JoinColumn(name = "PART_ID"))
 */