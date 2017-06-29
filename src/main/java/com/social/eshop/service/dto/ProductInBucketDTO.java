package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ProductInBucket entity.
 */
public class ProductInBucketDTO implements Serializable {

    private Long id;

    private Long bucketId;

    private Long productsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductInBucketDTO productInBucketDTO = (ProductInBucketDTO) o;
        if(productInBucketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productInBucketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductInBucketDTO{" +
            "id=" + getId() +
            "}";
    }
}
