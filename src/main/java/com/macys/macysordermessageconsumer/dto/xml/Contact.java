package com.macys.macysordermessageconsumer.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class Contact implements Serializable {

    @JacksonXmlProperty(localName = "custID")
    public int custID;

    @JacksonXmlProperty(localName = "name")
    public Name name;

    @JacksonXmlProperty(localName = "address")
    public Address address;

    @JacksonXmlProperty(localName = "daytimePhoneNbr")
    public String daytimePhoneNbr;

    @JacksonXmlProperty(localName = "homePhoneNbr")
    public String homePhoneNbr;

    @JacksonXmlProperty(localName = "alternatePhoneNbr")
    public String alternatePhoneNbr;

    @JacksonXmlProperty(localName = "sendSMSMessage")
    public boolean sendSMSMessage;

    @JacksonXmlProperty(localName = "emailAddress")
    public String emailAddress;

    @JacksonXmlProperty(localName = "seperatorContact0")
    public int separatorContact0;

    public Contact() {
    }

    @JacksonXmlProperty(localName = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustID() {
        return custID;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDaytimePhoneNbr() {
        return daytimePhoneNbr;
    }

    public void setDaytimePhoneNbr(String daytimePhoneNbr) {
        this.daytimePhoneNbr = daytimePhoneNbr;
    }

    public String getHomePhoneNbr() {
        return homePhoneNbr;
    }

    public void setHomePhoneNbr(String homePhoneNbr) {
        this.homePhoneNbr = homePhoneNbr;
    }

    public String getAlternatePhoneNbr() {
        return alternatePhoneNbr;
    }

    public void setAlternatePhoneNbr(String alternatePhoneNbr) {
        this.alternatePhoneNbr = alternatePhoneNbr;
    }

    public boolean isSendSMSMessage() {
        return sendSMSMessage;
    }

    public void setSendSMSMessage(boolean sendSMSMessage) {
        this.sendSMSMessage = sendSMSMessage;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getSeparatorContact0() {
        return separatorContact0;
    }

    public void setSeparatorContact0(int separatorContact0) {
        this.separatorContact0 = separatorContact0;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "custID=" + custID +
                ", name=" + name +
                ", address=" + address +
                ", daytimePhoneNbr='" + daytimePhoneNbr + '\'' +
                ", homePhoneNbr='" + homePhoneNbr + '\'' +
                ", alternatePhoneNbr='" + alternatePhoneNbr + '\'' +
                ", sendSMSMessage=" + sendSMSMessage +
                ", emailAddress='" + emailAddress + '\'' +
                ", separatorContact0=" + separatorContact0 +
                '}';
    }
}