package com.tw.hibernate.basics;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "cats")
@Inheritance(strategy = javax.persistence.InheritanceType.SINGLE_TABLE)
@DiscriminatorValue("C")
@DiscriminatorColumn(name = "subclass", discriminatorType = javax.persistence.DiscriminatorType.CHAR)

public class Cat {

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    private Integer id;

    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    private BigDecimal weight;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(updatable = false)
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    private Date birthdate;


//    @org.hibernate.annotations.Type(type = "eg.types.ColorUserType")
//    @NotNull
//    @Column(updatable = false)
//    public ColorType getColor() {
//        return color;
//    }
//    public void setColor(ColorType color) {
//        this.color = color;
//    }
//    private ColorType color;

    @NotNull
    @Column(updatable = false)
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    private String sex;

    @NotNull
    @Column(updatable = false)
    public Integer getLitterId() {
        return litterId;
    }
    public void setLitterId(Integer litterId) {
        this.litterId = litterId;
    }
    private Integer litterId;

    @ManyToOne
    @JoinColumn(name = "mother_id", updatable = false)
    public Cat getMother() {
        return mother;
    }
    public void setMother(Cat mother) {
        this.mother = mother;
    }
    private Cat mother;

    @OneToMany(mappedBy = "mother")
    @OrderBy("litterId")
    public Set<Cat> getKittens() {
        return kittens;
    }
    public void setKittens(Set<Cat> kittens) {
        this.kittens = kittens;
    }
    private Set<Cat> kittens = new HashSet<Cat>();

}
