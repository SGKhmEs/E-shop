package com.social.eshop.service.dto;

<<<<<<< HEAD
import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Media;
import com.social.eshop.domain.SubCategory;
import com.social.eshop.service.mapper.AutoMapping;

import java.math.BigDecimal;
import java.util.List;

public class ProductsDTO implements AutoMapping {

    private String name;
    private BigDecimal price;
    private String sale;
    private double rating;
    private boolean fresh;
    private List<Media> media;
    private List<Comments> comments;
    private List<SubCategory> subCategories;

    public ProductsDTO() { }

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
=======

import javax.validation.constraints.*;
import java.io.Serializable;
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

    private String sale;

    private Double rating;

    private Boolean fresh;

    private Long wishListId;

    private Long seenId;

    private Long bucketId;

    private Long subCategoryId;

    private Long mediaId;

    private Long tagsId;

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
>>>>>>> with_entities
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

<<<<<<< HEAD
    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
=======
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

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getSeenId() {
        return seenId;
    }

    public void setSeenId(Long seenId) {
        this.seenId = seenId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
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

        ProductsDTO productsDTO = (ProductsDTO) o;
        if(productsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
>>>>>>> with_entities
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
<<<<<<< HEAD
            "name='" + name + '\'' +
            ", price=" + price +
            ", sale='" + sale + '\'' +
            ", rating=" + rating +
            ", fresh=" + fresh +
            ", media=" + media +
            ", comments=" + comments +
            ", subCategories=" + subCategories +
            '}';
=======
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sale='" + getSale() + "'" +
            ", rating='" + getRating() + "'" +
            ", fresh='" + isFresh() + "'" +
            "}";
>>>>>>> with_entities
    }
}
