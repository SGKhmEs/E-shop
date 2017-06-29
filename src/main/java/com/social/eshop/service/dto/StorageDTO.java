package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Storage entity.
 */
public class StorageDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Boolean actualite;

    @NotNull
    private Integer quantity;

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

    public Boolean isActualite() {
        return actualite;
    }

    public void setActualite(Boolean actualite) {
        this.actualite = actualite;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StorageDTO storageDTO = (StorageDTO) o;
        if(storageDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), storageDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StorageDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", actualite='" + isActualite() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }
}
