package com.macys.macysordermessageconsumer.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.macys.macysordermessageconsumer.dto.OrderStatus;

import java.io.Serializable;

@JacksonXmlRootElement(localName = "FulfillmentOrder", namespace = "ns0:http://www.mst.macys.com/main/eos/fulfillmentOrder/orderDetails.v1.1")
public class FulfillmentOrder implements Serializable {

    @JacksonXmlProperty(localName = "orderID")
    public int orderID;

    @JacksonXmlProperty(localName = "orderTypeCode")
    public String orderTypeCode;

    @JacksonXmlProperty(localName = "partnerOrderID")
    public String partnerOrderID;

    @JacksonXmlProperty(localName = "orderStatus")
    public OrderStatus orderStatus = OrderStatus.CREATED;

    @JacksonXmlProperty(localName = "messageCreateTimeStamp")
    public String messageCreateTimeStamp;

    @JacksonXmlProperty(localName = "fulfillmentChannelCode")
    public String fulfillmentChannelCode;

    @JacksonXmlProperty(localName = "orderStatusCode")
    public int orderStatusCode;

    @JacksonXmlProperty(localName = "orderStatusDescription")
    public String orderStatusDescription;

    @JacksonXmlProperty(localName = "sellZLDivisionNbr")
    public int sellZLDivisionNbr;

    @JacksonXmlProperty(localName = "sellZLLocationNbr")
    public int sellZLLocationNbr;

    @JacksonXmlProperty(localName = "source")
    public Source source;

    @JacksonXmlProperty(localName = "orderTotals")
    public OrderTotals orderTotals;

    @JacksonXmlProperty(localName = "billingAddress")
    public BillingAddress billingAddress;

    public FulfillmentOrder() {
    }

    @JacksonXmlProperty(localName = "id")
    private int id;

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
        return "FulfillmentOrder{" +
                "orderID=" + orderID +
                ", orderTypeCode='" + orderTypeCode + '\'' +
                ", partnerOrderID='" + partnerOrderID + '\'' +
                ", orderStatus='" + orderStatus.getStatus() + '\'' +
                ", messageCreateTimeStamp='" + messageCreateTimeStamp + '\'' +
                ", fulfillmentChannelCode='" + fulfillmentChannelCode + '\'' +
                ", orderStatusCode=" + orderStatusCode +
                ", orderStatusDescription='" + orderStatusDescription + '\'' +
                ", sellZLDivisionNbr=" + sellZLDivisionNbr +
                ", sellZLLocationNbr=" + sellZLLocationNbr +
                ", source=" + source +
                ", orderTotals=" + orderTotals +
                ", billingAddress=" + billingAddress +
                '}';
    }
}