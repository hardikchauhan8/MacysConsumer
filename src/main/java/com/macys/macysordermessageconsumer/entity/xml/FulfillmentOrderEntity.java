package com.macys.macysordermessageconsumer.entity.xml;

import com.macys.macysordermessageconsumer.dto.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ORDER_MESSAGE_XML")
public class FulfillmentOrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    public Source source;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    public OrderTotals orderTotals;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="id")
    public BillingAddress billingAddress;

    @Column(name = "orderID")
    public int orderID;

    @Column(name = "orderTypeCode")
    public String orderTypeCode;

    @Column(name = "partnerOrderID")
    public String partnerOrderID;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    public OrderStatus orderStatus;

    @Column(name = "messageCreateTimeStamp")
    public String messageCreateTimeStamp;

    @Column(name = "fulfillmentChannelCode")
    public String fulfillmentChannelCode;

    @Column(name = "orderStatusCode")
    public int orderStatusCode;

    @Column(name = "orderStatusDescription")
    public String orderStatusDescription;

    @Column(name = "sellZLDivisionNbr")
    public int sellZLDivisionNbr;

    @Column(name = "sellZLLocationNbr")
    public int sellZLLocationNbr;

    public FulfillmentOrderEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public String getPartnerOrderID() {
        return partnerOrderID;
    }

    public void setPartnerOrderID(String partnerOrderID) {
        this.partnerOrderID = partnerOrderID;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getMessageCreateTimeStamp() {
        return messageCreateTimeStamp;
    }

    public void setMessageCreateTimeStamp(String messageCreateTimeStamp) {
        this.messageCreateTimeStamp = messageCreateTimeStamp;
    }

    public String getFulfillmentChannelCode() {
        return fulfillmentChannelCode;
    }

    public void setFulfillmentChannelCode(String fulfillmentChannelCode) {
        this.fulfillmentChannelCode = fulfillmentChannelCode;
    }

    public int getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(int orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }

    public int getSellZLDivisionNbr() {
        return sellZLDivisionNbr;
    }

    public void setSellZLDivisionNbr(int sellZLDivisionNbr) {
        this.sellZLDivisionNbr = sellZLDivisionNbr;
    }

    public int getSellZLLocationNbr() {
        return sellZLLocationNbr;
    }

    public void setSellZLLocationNbr(int sellZLLocationNbr) {
        this.sellZLLocationNbr = sellZLLocationNbr;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public OrderTotals getOrderTotals() {
        return orderTotals;
    }

    public void setOrderTotals(OrderTotals orderTotals) {
        this.orderTotals = orderTotals;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "FulfillmentOrderEntity{" +
                "id=" + id +
                ", source=" + source +
                ", orderTotals=" + orderTotals +
                ", billingAddress=" + billingAddress +
                ", orderID=" + orderID +
                ", orderTypeCode='" + orderTypeCode + '\'' +
                ", partnerOrderID='" + partnerOrderID + '\'' +
                ", orderStatus='" + orderStatus.getStatus() + '\'' +
                ", messageCreateTimeStamp='" + messageCreateTimeStamp + '\'' +
                ", fulfillmentChannelCode='" + fulfillmentChannelCode + '\'' +
                ", orderStatusCode=" + orderStatusCode +
                ", orderStatusDescription='" + orderStatusDescription + '\'' +
                ", sellZLDivisionNbr=" + sellZLDivisionNbr +
                ", sellZLLocationNbr=" + sellZLLocationNbr +
                '}';
    }
}