package com.macys.macysordermessageconsumer.dto.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.macys.macysordermessageconsumer.dto.OrderStatus;

import java.io.Serializable;


public class OrderMessageJson implements Serializable {

    @JsonProperty("messageName")
    private String messageName;

    @JsonProperty("imagePathname")
    private String imagePathname;

    @JsonProperty("itemHeight")
    private double itemHeight;

    @JsonProperty("upcList")
    private String upcList;

    @JsonProperty("itemLength")
    private double itemLength;

    @JsonProperty("command")
    private String command;

    @JsonProperty("itemName")
    private String itemName;

    @JsonProperty("pickType")
    private String pickType;

    @JsonProperty("itemWidth")
    private double itemWidth;

    @JsonProperty("rfidTagged")
    private String rfidTagged;

    @JsonProperty("itemDescription")
    private String itemDescription;

    @JsonProperty("storageAttribute")
    private int storageAttribute;

    @JsonProperty("itemWeight")
    private double itemWeight;

    @JsonProperty("orderStatus")
    private OrderStatus orderStatus = OrderStatus.CREATED;

    public OrderMessageJson() {
    }

    public OrderMessageJson(String messageName, String imagePathname, double itemHeight, String upcList, double itemLength, String command, String itemName, String pickType, double itemWidth, String rfidTagged, String itemDescription, int storageAttribute, double itemWeight, OrderStatus orderStatus) {
        this.messageName = messageName;
        this.imagePathname = imagePathname;
        this.itemHeight = itemHeight;
        this.upcList = upcList;
        this.itemLength = itemLength;
        this.command = command;
        this.itemName = itemName;
        this.pickType = pickType;
        this.itemWidth = itemWidth;
        this.rfidTagged = rfidTagged;
        this.itemDescription = itemDescription;
        this.storageAttribute = storageAttribute;
        this.itemWeight = itemWeight;
        this.orderStatus = orderStatus;
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