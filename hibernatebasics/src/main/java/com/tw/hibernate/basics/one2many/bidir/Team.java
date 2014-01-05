package com.tw.hibernate.basics.one2many.bidir;

import com.tw.hibernate.basics.other.Color;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Map;

@Entity
public class Team {

    private Integer id;
    private String name;
    private Color color;
    private Map<Integer, Player> players;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(value = EnumType.STRING)
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @OneToMany(mappedBy = "team")
//    @MapKey
    @MapKey(name = "jerseyNumber")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public Map<Integer, Player> getPlayers() {
        return players;
    }

    public void setPlayers(Map<Integer, Player> players) {
        this.players = players;
    }
}
