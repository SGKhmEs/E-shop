package com.social.eshop.service.dto;

import com.social.eshop.domain.Seen;
import com.social.eshop.domain.WishList;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class CustomerDTO implements AutoMapping {

    private String sessionId;
    private List<WishList> wishLists;
    private List<Seen> seens;
    private List<ProductsDTO> products;

    public CustomerDTO() { }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public List<Seen> getSeens() {
        return seens;
    }

    public void setSeens(List<Seen> seens) {
        this.seens = seens;
    }

    public List<ProductsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "sessionId='" + sessionId + '\'' +
            ", wishLists=" + wishLists +
            ", seens=" + seens +
            ", products=" + products +
            '}';
    }
}
