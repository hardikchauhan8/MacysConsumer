package com.macys.macysordermessageconsumer.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class BillingAddress implements Serializable {

    @JacksonXmlProperty(localName = "contact")
    public Contact contact;

    public BillingAddress() {

    }

    @JacksonXmlProperty(localName = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "contact=" + contact +
                '}';
    }
}