package com.social.eshop.service.dto;

import com.social.eshop.domain.Consignment;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class AdministratorDTO implements AutoMapping {

    private List<BucketDTO> buckets;
    private List<ProductsDTO> products;
    private List<Consignment> consignments;

    public AdministratorDTO() { }

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

    public List<Consignment> getConsignments() {
        return consignments;
    }

    public void setConsignments(List<Consignment> consignments) {
        this.consignments = consignments;
    }

    @Override
    public String toString() {
        return "AdministratorDTO{" +
            "buckets=" + buckets +
            ", products=" + products +
            ", consignments=" + consignments +
            '}';
    }
}
