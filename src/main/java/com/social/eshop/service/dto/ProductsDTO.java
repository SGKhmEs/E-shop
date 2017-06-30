package com.social.eshop.service.dto;


import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Options;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Products entity.
 */
public class ProductsDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    private Integer sale;

    private Double rating;

    private Boolean fresh;

    private String description;

    private OptionsDTO optionsDTO;

    private SubCategoryDTO subCategoryDTO;

    private Set<CommentsDTO> commentsDTO;

    private Set<MediaDTO> mediaDTO;
    //private Long optionsId;

//  private Long subCategoryId;



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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean isFresh() {
        return fresh;
    }

    public void setFresh(Boolean fresh) {
        this.fresh = fresh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OptionsDTO getOptionsDTO() {
        return optionsDTO;
    }

    public void setOptionsDTO(OptionsDTO optionsDTO) {
        this.optionsDTO = optionsDTO;
    }

    public SubCategoryDTO getSubCategoryDTO() {
        return subCategoryDTO;
    }

    public void setSubCategoryDTO(SubCategoryDTO subCategoryDTO) {
        this.subCategoryDTO = subCategoryDTO;
    }

    public Set<CommentsDTO> getCommentsDTO() {
        return commentsDTO;
    }

    public void setCommentsDTO(Set<CommentsDTO> commentsDTO) {
        this.commentsDTO = commentsDTO;
    }

    public Set<MediaDTO> getMediaDTO() {
        return mediaDTO;
    }

    public void setMediaDTO(Set<MediaDTO> mediaDTO) {
        this.mediaDTO = mediaDTO;
    }

    //    public Long getOptionsId() {
//        return optionsId;
//    }
//
//    public void setOptionsId(Long optionsId) {
//        this.optionsId = optionsId;
//    }

//    public Long getSubCategoryId() {
//        return subCategoryId;
//    }
//
//    public void setSubCategoryId(Long subCategoryId) {
//        this.subCategoryId = subCategoryId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductsDTO productsDTO = (ProductsDTO) o;
        if(productsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", sale='" + getSale() + "'" +
            ", rating='" + getRating() + "'" +
            ", fresh='" + isFresh() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
