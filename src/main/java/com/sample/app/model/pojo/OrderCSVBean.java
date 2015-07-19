package com.sample.app.model.pojo;

public class OrderCSVBean {
    private String deliveryDate;
    private String deliveryShift;
    private String originName;
    private String originRawLine;
    private String originCity;
    private String originState;
    private String originZip;
    private String originCountry;
    private String clientName;
    private String destinationRawLine;
    private String destinationCity;
    private String destinationState;
    private String destinationZip;
    private String destinationCountry;
    private String phoneNumber;
    private String purchaseOrderNumber;
    private String volume;
    private String handlingUnitQuantity;

    public OrderCSVBean() {
    }

    public OrderCSVBean(String deliveryDate, String deliveryShift, String originName, String originRawLine, String originCity, String originState, String originZip, String originCountry, String clientName, String destinationRawLine, String destinationCity, String destinationState, String destinationZip, String destinationCountry, String phoneNumber, String purchaseOrderNumber, String volume, String handlingUnitQuantity) {
        this.deliveryDate = deliveryDate;
        this.deliveryShift = deliveryShift;
        this.originName = originName;
        this.originRawLine = originRawLine;
        this.originCity = originCity;
        this.originState = originState;
        this.originZip = originZip;
        this.originCountry = originCountry;
        this.clientName = clientName;
        this.destinationRawLine = destinationRawLine;
        this.destinationCity = destinationCity;
        this.destinationState = destinationState;
        this.destinationZip = destinationZip;
        this.destinationCountry = destinationCountry;
        this.phoneNumber = phoneNumber;
        this.purchaseOrderNumber = purchaseOrderNumber;
        this.volume = volume;
        this.handlingUnitQuantity = handlingUnitQuantity;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryShift() {
        return deliveryShift;
    }

    public void setDeliveryShift(String deliveryShift) {
        this.deliveryShift = deliveryShift;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getOriginRawLine() {
        return originRawLine;
    }

    public void setOriginRawLine(String originRawLine) {
        this.originRawLine = originRawLine;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getOriginState() {
        return originState;
    }

    public void setOriginState(String originState) {
        this.originState = originState;
    }

    public String getOriginZip() {
        return originZip;
    }

    public void setOriginZip(String originZip) {
        this.originZip = originZip;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getDestinationRawLine() {
        return destinationRawLine;
    }

    public void setDestinationRawLine(String destinationRawLine) {
        this.destinationRawLine = destinationRawLine;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDestinationState() {
        return destinationState;
    }

    public void setDestinationState(String destinationState) {
        this.destinationState = destinationState;
    }

    public String getDestinationZip() {
        return destinationZip;
    }

    public void setDestinationZip(String destinationZip) {
        this.destinationZip = destinationZip;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getHandlingUnitQuantity() {
        return handlingUnitQuantity;
    }

    public void setHandlingUnitQuantity(String handlingUnitQuantity) {
        this.handlingUnitQuantity = handlingUnitQuantity;
    }
}
