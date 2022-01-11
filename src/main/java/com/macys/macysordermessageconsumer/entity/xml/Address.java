package com.macys.macysordermessageconsumer.entity.xml;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "line1")
    public String line1;

    @Column(name = "line2")
    public String line2;

    @Column(name = "line3")
    public String line3;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    @Column(name = "zipCode")
    public int zipCode;

    @Column(name = "countryCode")
    public String countryCode;

    @Column(name = "seperatorAddress0")
    public int separatorAddress0;

    public Address() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getSeparatorAddress0() {
        return separatorAddress0;
    }

    public void setSeparatorAddress0(int separatorAddress0) {
        this.separatorAddress0 = separatorAddress0;
    }

    @Override
    public String toString() {
        return "Address{" +
                "line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", line3='" + line3 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", countryCode='" + countryCode + '\'' +
                ", separatorAddress0=" + separatorAddress0 +
                '}';
    }
}