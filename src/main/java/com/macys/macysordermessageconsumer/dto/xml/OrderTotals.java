package com.macys.macysordermessageconsumer.dto.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

public class OrderTotals implements Serializable {

    @JacksonXmlProperty(localName = "totalPurchaseAmount")
    public double totalPurchaseAmount;

    @JacksonXmlProperty(localName = "seperatorOrderTotals0")
    public int separatorOrderTotals0;

    public OrderTotals() {
    }

    @JacksonXmlProperty(localName = "id")
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(double totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public int getSeparatorOrderTotals0() {
        return separatorOrderTotals0;
    }

    public void setSeparatorOrderTotals0(int separatorOrderTotals0) {
        this.separatorOrderTotals0 = separatorOrderTotals0;
    }

    @Override
    public String toString() {
        return "OrderTotals{" +
                "totalPurchaseAmount=" + totalPurchaseAmount +
                ", separatorOrderTotals0=" + separatorOrderTotals0 +
                '}';
    }
}