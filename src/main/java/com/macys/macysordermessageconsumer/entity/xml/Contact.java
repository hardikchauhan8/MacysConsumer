package com.macys.macysordermessageconsumer.entity.xml;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "CONTACT")
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "custID")
    public int custID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    public Name name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    public Address address;

    @Column(name = "daytimePhoneNbr")
    public String daytimePhoneNbr;

    @Column(name = "homePhoneNbr")
    public String homePhoneNbr;

    @Column(name = "alternatePhoneNbr")
    public String alternatePhoneNbr;

    @Column(name = "sendSMSMessage")
    public boolean sendSMSMessage;

    @Column(name = "emailAddress")
    public String emailAddress;

    @Column(name = "seperatorContact0")
    public int separatorContact0;

    public Contact() {
    }

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