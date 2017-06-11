package com.social.eshop.service.dto;

import com.social.eshop.domain.AddressShipping;
import com.social.eshop.domain.enumeration.Status;
import com.social.eshop.service.mapper.AutoMapping;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BucketDTO implements AutoMapping{

    private String name;
    private ZonedDateTime dateTime;
    private BigDecimal sum;
    private int orderNumber;
    private Status status;
    private int count;
    private String consignmentNote;
    private AddressShipping addressShipping;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(ZonedDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getConsignmentNote() {
        return consignmentNote;
    }

    public void setConsignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public AddressShipping getAddressShipping() {
        return addressShipping;
    }

    public void setAddressShipping(AddressShipping addressShipping) {
        this.addressShipping = addressShipping;
    }

    @Override
    public String toString() {
        return "BucketDTO{" +
            "name='" + name + '\'' +
            ", dateTime=" + dateTime +
            ", sum=" + sum +
            ", orderNumber=" + orderNumber +
            ", status=" + status +
            ", count=" + count +
            ", consignmentNote='" + consignmentNote + '\'' +
            ", addressShipping=" + addressShipping +
            '}';
    }

}
