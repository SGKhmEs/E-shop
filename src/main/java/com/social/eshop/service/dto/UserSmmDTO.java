package com.social.eshop.service.dto;

import com.social.eshop.domain.StaticPages;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class UserSmmDTO implements AutoMapping {

    private List<StaticPages> staticPages;

    public UserSmmDTO() { }

    public List<StaticPages> getStaticPages() {
        return staticPages;
    }

    public void setStaticPages(List<StaticPages> staticPages) {
        this.staticPages = staticPages;
    }

    @Override
    public String toString() {
        return "UserSmmDTO{" +
            "staticPages=" + staticPages +
            '}';
    }
}
