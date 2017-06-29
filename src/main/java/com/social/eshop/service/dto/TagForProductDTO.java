package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the TagForProduct entity.
 */
public class TagForProductDTO implements Serializable {

    private Long id;

    private Long productsId;

    private Long tagsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TagForProductDTO tagForProductDTO = (TagForProductDTO) o;
        if(tagForProductDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tagForProductDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TagForProductDTO{" +
            "id=" + getId() +
            "}";
    }
}
