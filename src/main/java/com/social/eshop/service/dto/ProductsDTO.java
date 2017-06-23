package com.social.eshop.service.dto;

import com.social.eshop.domain.Media;
import com.social.eshop.domain.Options;
import com.social.eshop.domain.SubCategory;

import java.math.BigDecimal;
import java.util.List;

public class ProductsDTO {
    private String name;
    private BigDecimal price;
    private int sale;
    private double rating;
    private boolean fresh;
    private String description;
    private List<Media> media;
    private List<CommentsDTO> commentsDTO;
    private List<SubCategory> subCategories;
    private Options options;

    public ProductsDTO() {}

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

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<CommentsDTO> getCommentsDTO() {
        return commentsDTO;
    }

    public void setCommentsDTO(List<CommentsDTO> commentsDTO) {
        this.commentsDTO = commentsDTO;
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ProductsDTO{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", sale=" + sale +
            ", rating=" + rating +
            ", fresh=" + fresh +
            ", description='" + description + '\'' +
            ", media=" + media +
            ", commentsDTO=" + commentsDTO +
            ", subCategories=" + subCategories +
            ", options=" + options +
            '}';
    }
}
