package com.social.eshop.service.dto;

<<<<<<< HEAD
import com.social.eshop.domain.AddressShipping;
import com.social.eshop.domain.enumeration.Status;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class BucketDTO {
    private String name;
    private ZonedDateTime dateTime;
    private BigDecimal sum;
    private int orderNumber;
    private Status status;
    private int count;
    private String consignmentNote;
    private AddressShipping addressShipping;
    private ProductsDTO productsDTO;

    public BucketDTO() {}

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
=======

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Bucket entity.
 */
public class BucketDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
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
>>>>>>> with_entities
    }

    public ProductsDTO getProductsDTO() {
        return productsDTO;
    }

    public void setProductsDTO(ProductsDTO productsDTO) {
        this.productsDTO = productsDTO;
    }

    @Override
    public String toString() {
        return "BucketDTO{" +
<<<<<<< HEAD
            "name='" + name + '\'' +
            ", dateTime=" + dateTime +
            ", sum=" + sum +
            ", orderNumber=" + orderNumber +
            ", status=" + status +
            ", count=" + count +
            ", consignmentNote='" + consignmentNote + '\'' +
            ", addressShipping=" + addressShipping +
            ", productsDTO=" + productsDTO +
            '}';
    }
<<<<<<< HEAD

=======
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
>>>>>>> with_entities
=======
>>>>>>> creatingDtos
}
