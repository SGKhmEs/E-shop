package com.social.eshop.service.dto;

<<<<<<< HEAD
import com.social.eshop.domain.Customer;
import com.social.eshop.service.mapper.AutoMapping;

import java.time.ZonedDateTime;

public class CommentsDTO implements AutoMapping {

    private String comments;
    private ZonedDateTime date;
    private Customer customer;

    public CommentsDTO() { }
=======

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Comments entity.
 */
public class CommentsDTO implements Serializable {

    private Long id;

    private String comments;

    private ZonedDateTime data;

    private Long customerRoomId;

    private Long productsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
>>>>>>> with_entities

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

<<<<<<< HEAD
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
=======
    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
        this.data = data;
    }

    public Long getCustomerRoomId() {
        return customerRoomId;
    }

    public void setCustomerRoomId(Long customerRoomId) {
        this.customerRoomId = customerRoomId;
    }

    public Long getProductsId() {
        return productsId;
    }

    public void setProductsId(Long productsId) {
        this.productsId = productsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentsDTO commentsDTO = (CommentsDTO) o;
        if(commentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
>>>>>>> with_entities
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
<<<<<<< HEAD
            "comments='" + comments + '\'' +
            ", date=" + date +
            ", customer=" + customer +
            '}';
=======
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", data='" + getData() + "'" +
            "}";
>>>>>>> with_entities
    }
}
