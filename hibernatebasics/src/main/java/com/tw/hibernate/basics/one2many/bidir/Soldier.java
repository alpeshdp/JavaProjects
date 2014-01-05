package com.tw.hibernate.basics.one2many.bidir;

import javax.persistence.*;

@Entity
public class Soldier {

    private int id;
    private String fName;
    private String lName;
    private Troop troop;

    @Deprecated
    public Soldier() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    void setId(int id) {
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

    @ManyToOne
    @JoinColumn(name = "troop_fk", insertable = false, updatable = false)
    public Troop getTroop() {
        return troop;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }
}
