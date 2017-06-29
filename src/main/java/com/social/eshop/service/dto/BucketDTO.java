package com.social.eshop.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Status;

/**
 * A DTO for the Bucket entity.
 */
public class BucketDTO implements Serializable {

    private Long id;

    private String name;

    private ZonedDateTime date;

    private BigDecimal sum;

    private Integer orderNumber;

    private Integer count;

    private Status status;

    private String consignmentNote;

    private Long managerId;

    private Long addressShippingId;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getConsignmentNote() {
        return consignmentNote;
    }

    public void setConsignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getAddressShippingId() {
        return addressShippingId;
    }

    public void setAddressShippingId(Long addressShippingId) {
        this.addressShippingId = addressShippingId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BucketDTO bucketDTO = (BucketDTO) o;
        if(bucketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bucketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BucketDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", date='" + getDate() + "'" +
            ", sum='" + getSum() + "'" +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", count='" + getCount() + "'" +
            ", status='" + getStatus() + "'" +
            ", consignmentNote='" + getConsignmentNote() + "'" +
            "}";
    }
}
