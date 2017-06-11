package com.social.eshop.service.dto;

import com.social.eshop.domain.Comments;
import com.social.eshop.domain.Media;
import com.social.eshop.service.mapper.AutoMapping;

import java.math.BigDecimal;
import java.util.List;

public class ProductsDTO implements AutoMapping {

    private String name;
    private BigDecimal price;
    private String sale;
    private double rating;
    private boolean fresh;
    private Media media;
    private List<Comments> comments;

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

    public boolean isFresh() {
        return fresh;
    }

    public void setFresh(boolean fresh) {
        this.fresh = fresh;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
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
            ", comments=" + comments +
            '}';
    }

}
