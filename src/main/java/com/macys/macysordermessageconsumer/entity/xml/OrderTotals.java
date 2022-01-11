package com.macys.macysordermessageconsumer.entity.xml;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ORDER_TOTALS")
public class OrderTotals implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "totalPurchaseAmount")
    public double totalPurchaseAmount;

    @Column(name = "seperatorOrderTotals0")
    public int separatorOrderTotals0;

    public OrderTotals() {
    }

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