package com.social.eshop.service.dto;

import com.social.eshop.domain.Consignment;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class OwnerDTO implements AutoMapping {

    private List<BucketDTO> buckets;
    private List<ProductsDTO> products;
    private List<CommentsDTO> comments;
    private List<Consignment> consignments;

    public OwnerDTO() { }

    public List<BucketDTO> getBuckets() {
        return buckets;
    }

    public void setBuckets(List<BucketDTO> buckets) {
        this.buckets = buckets;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
    }

    public List<CommentsDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentsDTO> comments) {
        this.comments = comments;
    }

    public List<Consignment> getConsignments() {
        return consignments;
    }

    public void setConsignments(List<Consignment> consignments) {
        this.consignments = consignments;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
            "buckets=" + buckets +
            ", products=" + products +
            ", comments=" + comments +
            ", consignments=" + consignments +
            '}';
    }
}
