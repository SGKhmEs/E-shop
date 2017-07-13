package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Consignment entity.
 */
public class ConsignmentDTO implements Serializable {

    private Long id;

    @NotNull
    private BigDecimal price;

    private Long storageId;

    private Long producersId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public Long getProducersId() {
        return producersId;
    }

    public void setProducersId(Long producersId) {
        this.producersId = producersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsignmentDTO consignmentDTO = (ConsignmentDTO) o;
        if(consignmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consignmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsignmentDTO{" +
            "id=" + getId() +
            ", price='" + getPrice() + "'" +
            "}";
    }
}
