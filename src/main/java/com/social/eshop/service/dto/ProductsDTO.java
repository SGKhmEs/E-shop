package com.social.eshop.service.dto;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Media;
import com.social.eshop.domain.SubCategory;
import com.social.eshop.service.mapper.AutoMapping;

import java.math.BigDecimal;
import java.util.List;

public class   ProductsDTO implements AutoMapping {

    private String name;
    private BigDecimal price;
    private String sale;
    private double rating;
    private boolean fresh;
    private List<Media> media;
    private List<CommentsDTO> commentsDTO;
    private List<SubCategory> subCategories;

    public ProductsDTO() { }

    public ProductsDTO(String name, BigDecimal price,
                       String sale, double rating,
                       boolean fresh, List<Media> media,
                       List<CommentsDTO> commentsDTO,
                       List<SubCategory> subCategories) {
        this.name = name;
        this.price = price;
        this.sale = sale;
        this.rating = rating;
        this.fresh = fresh;
        this.media = media;
        this.commentsDTO = commentsDTO;
        this.subCategories = subCategories;
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

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
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

    @Override
    public String toString() {
        return "ProductsDTO{" +
            "name='" + name + '\'' +
            ", price=" + price +
            ", sale='" + sale + '\'' +
            ", rating=" + rating +
            ", fresh=" + fresh +
            ", media=" + media +
            ", commentsDTO=" + commentsDTO +
            ", subCategories=" + subCategories +
            '}';
    }

}
