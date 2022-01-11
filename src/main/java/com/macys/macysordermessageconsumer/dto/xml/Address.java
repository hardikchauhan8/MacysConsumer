package com.macys.macysordermessageconsumer.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class Address implements Serializable {

    @JacksonXmlProperty(localName = "line1")
    public String line1;

    @JacksonXmlProperty(localName = "line2")
    public String line2;

    @JacksonXmlProperty(localName = "line3")
    public String line3;

    @JacksonXmlProperty(localName = "city")
    public String city;

    @JacksonXmlProperty(localName = "state")
    public String state;

    @JacksonXmlProperty(localName = "zipCode")
    public int zipCode;

    @JacksonXmlProperty(localName = "countryCode")
    public String countryCode;

    @JacksonXmlProperty(localName = "seperatorAddress0")
    public int separatorAddress0;

    public Address() {

    }

    @JacksonXmlProperty(localName = "id")
    private int id;

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