package com.tw.hibernate.basics.one2many.bidir;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"team_id", "jerseyNumber"})
})
public class Player {
    private Integer id;
    private Integer jerseyNumber;
    private String name;
    private Team team;
    List<String> nickNames;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }

    public Integer getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(Integer jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

//    @ElementCollection
//    public List<String> getNickNames() {
//        return nickNames;
//    }
//
//    public void setNickNames(List<String> nickNames) {
//        this.nickNames = nickNames;
//    }
}
