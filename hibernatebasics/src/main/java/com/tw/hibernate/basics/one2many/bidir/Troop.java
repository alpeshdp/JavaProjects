package com.tw.hibernate.basics.one2many.bidir;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;


@Entity
public class Troop {

    private int id;
    private String name;
    private List<Soldier> soldiers;

    @Deprecated
    public Troop() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinColumn(name = "troop_fk")
    @Cascade(value = CascadeType.SAVE_UPDATE)
    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }
}
