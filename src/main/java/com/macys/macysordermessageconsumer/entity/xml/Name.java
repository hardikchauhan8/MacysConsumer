package com.macys.macysordermessageconsumer.entity.xml;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "NAME")
public class Name implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "firstName")
    public String firstName;
    @Column(name = "lastName")
    public String lastName;
    @Column(name = "seperatorName0")
    public int separatorName0;
    @Column(name = "seperatorName1")
    public int separatorName1;

    public Name() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSeparatorName0() {
        return separatorName0;
    }

    public void setSeparatorName0(int separatorName0) {
        this.separatorName0 = separatorName0;
    }

    public int getSeparatorName1() {
        return separatorName1;
    }

    public void setSeparatorName1(int separatorName1) {
        this.separatorName1 = separatorName1;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", separatorName0=" + separatorName0 +
                ", separatorName1=" + separatorName1 +
                '}';
    }
}