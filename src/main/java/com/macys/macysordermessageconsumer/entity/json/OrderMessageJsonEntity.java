package com.macys.macysordermessageconsumer.entity.json;

import com.macys.macysordermessageconsumer.dto.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ORDER_MESSAGE_JSON")
public class OrderMessageJsonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "messageName")
    private String messageName;

    @Column(name = "imagePathname")
    private String imagePathname;

    @Column(name = "itemHeight")
    private double itemHeight;

    @Column(name = "upcList")
    private String upcList;

    @Column(name = "itemLength")
    private double itemLength;

    @Column(name = "command")
    private String command;

    @Column(name = "itemName")
    private String itemName;

    @Column(name = "pickType")
    private String pickType;

    @Column(name = "itemWidth")
    private double itemWidth;

    @Column(name = "rfidTagged")
    private String rfidTagged;

    @Column(name = "itemDescription")
    private String itemDescription;

    @Column(name = "storageAttribute")
    private int storageAttribute;

    @Column(name = "itemWeight")
    private double itemWeight;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderMessageJsonEntity() {

    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getImagePathname() {
        return imagePathname;
    }

    public void setImagePathname(String imagePathname) {
        this.imagePathname = imagePathname;
    }

    public double getItemHeight() {
        return itemHeight;
    }

    public void setItemHeight(double itemHeight) {
        this.itemHeight = itemHeight;
    }

    public String getUpcList() {
        return upcList;
    }

    public void setUpcList(String upcList) {
        this.upcList = upcList;
    }

    public double getItemLength() {
        return itemLength;
    }

    public void setItemLength(double itemLength) {
        this.itemLength = itemLength;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPickType() {
        return pickType;
    }

    public void setPickType(String pickType) {
        this.pickType = pickType;
    }

    public double getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(double itemWidth) {
        this.itemWidth = itemWidth;
    }

    public String getRfidTagged() {
        return rfidTagged;
    }

    public void setRfidTagged(String rfidTagged) {
        this.rfidTagged = rfidTagged;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getStorageAttribute() {
        return storageAttribute;
    }

    public void setStorageAttribute(int storageAttribute) {
        this.storageAttribute = storageAttribute;
    }

    public double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return
                "OrderMessageJson{" +
                        "messageName = '" + messageName + '\'' +
                        ",imagePathname = '" + imagePathname + '\'' +
                        ",itemHeight = '" + itemHeight + '\'' +
                        ",upcList = '" + upcList + '\'' +
                        ",itemLength = '" + itemLength + '\'' +
                        ",command = '" + command + '\'' +
                        ",itemName = '" + itemName + '\'' +
                        ",pickType = '" + pickType + '\'' +
                        ",itemWidth = '" + itemWidth + '\'' +
                        ",rfidTagged = '" + rfidTagged + '\'' +
                        ",itemDescription = '" + itemDescription + '\'' +
                        ",storageAttribute = '" + storageAttribute + '\'' +
                        ",itemWeight = '" + itemWeight + '\'' +
                        ",orderStatus = '" + orderStatus.getStatus() + '\'' +
                        "}";
    }
}