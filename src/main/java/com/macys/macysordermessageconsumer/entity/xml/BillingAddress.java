package com.macys.macysordermessageconsumer.entity.xml;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "BILLING_ADDRESS")
public class BillingAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    private Contact contact;

    public BillingAddress() {

    }

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