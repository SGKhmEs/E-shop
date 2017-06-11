package com.social.eshop.service.dto;

import com.social.eshop.domain.Customer;
import com.social.eshop.service.mapper.AutoMapping;

import java.time.ZonedDateTime;

public class CommentsDTO implements AutoMapping {

    private String comments;
    private ZonedDateTime date;
    private Customer customer;

    public CommentsDTO() { }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
            "comments='" + comments + '\'' +
            ", date=" + date +
            ", customer=" + customer +
            '}';
    }
}
