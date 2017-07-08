package com.social.eshop.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import com.social.eshop.domain.AddressShipping;
import com.social.eshop.domain.ProductInBucket;
import com.social.eshop.domain.Products;
import com.social.eshop.domain.enumeration.Status;

/**
 * A DTO for the Bucket entity.
 */
public class BucketDTO implements Serializable {

    private Long id;

    //private String name;

    private ZonedDateTime date;

    private BigDecimal sum;

    private Integer orderNumber;

    private Integer count;

    private Status status;

    private String consignmentNote;

    private Long managerId;

    private Long addressShippingId;  // in the future delete

    private AddressShipping addressShippingDTO;

    private Long customerId; // in the future delete

    private String customerName;

    private String customerPhone;

    private  Set<ProductInBucket> productInBuckets; // in the future delete

    private Set<ProductInBucketDTO> productInBucketDTOS;

    public Set<ProductInBucket> getProductInBuckets() {
        return productInBuckets;
    }

    public void setProductInBuckets(Set<ProductInBucket> productInBuckets) {
        this.productInBuckets = productInBuckets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AddressShipping getAddressShippingDTO() {
        return addressShippingDTO;
    }

    public void setAddressShippingDTO(AddressShipping addressShippingDTO) {
        this.addressShippingDTO = addressShippingDTO;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Set<ProductInBucketDTO> getProductInBucketDTOS() {
        return productInBucketDTOS;
    }

    public void setProductInBucketDTOS(Set<ProductInBucketDTO> productInBucketDTOS) {
        this.productInBucketDTOS = productInBucketDTOS;
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
            "id=" + id +
            ", date=" + date +
            ", sum=" + sum +
            ", orderNumber=" + orderNumber +
            ", count=" + count +
            ", status=" + status +
            ", consignmentNote='" + consignmentNote + '\'' +
            ", managerId=" + managerId +
            ", addressShippingId=" + addressShippingId +
            ", addressShippingDTO=" + addressShippingDTO +
            ", customerId=" + customerId +
            ", customerName='" + customerName + '\'' +
            ", customerPhone='" + customerPhone + '\'' +
            ", productInBuckets=" + productInBuckets +
            ", productInBucketDTOS=" + productInBucketDTOS +
            '}';
    }
}
