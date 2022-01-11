package com.macys.macysordermessageconsumer.entity.xml;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "SOURCE")
public class Source implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "clientID")
    private String clientID;

    @Column(name = "subClientID")
    private String subClientID;

    @Column(name = "sellingChannelCode")
    private String sellingChannelCode;

    @Column(name = "seperatorSource0")
    private int separatorSource0;

    @Column(name = "separatorSource1")
    private int separatorSource1;

    public Source() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getSubClientID() {
        return subClientID;
    }

    public void setSubClientID(String subClientID) {
        this.subClientID = subClientID;
    }

    public String getSellingChannelCode() {
        return sellingChannelCode;
    }

    public void setSellingChannelCode(String sellingChannelCode) {
        this.sellingChannelCode = sellingChannelCode;
    }

    public int getSeparatorSource0() {
        return separatorSource0;
    }

    public void setSeparatorSource0(int separatorSource0) {
        this.separatorSource0 = separatorSource0;
    }

    public int getSeparatorSource1() {
        return separatorSource1;
    }

    public void setSeparatorSource1(int separatorSource1) {
        this.separatorSource1 = separatorSource1;
    }

    @Override
    public String toString() {
        return "Source{" +
                "clientID='" + clientID + '\'' +
                ", subClientID='" + subClientID + '\'' +
                ", sellingChannelCode='" + sellingChannelCode + '\'' +
                ", separatorSource0=" + separatorSource0 +
                ", separatorSource1=" + separatorSource1 +
                '}';
    }
}