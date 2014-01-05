package com.tw.hibernate.basics;

import org.hibernate.annotations.AccessType;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@org.hibernate.annotations.Entity()
@Table(name = "TBL_FLIGHT",
        schema = "AIR_COMMAND",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"col1", "col2"})
        }
)
public class Flight implements Serializable {

    Long id;

    @Id()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    String name;
}