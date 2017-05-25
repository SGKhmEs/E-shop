package com.social.eshop.service.dto;


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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

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
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
            "id=" + getId() +
            ", comments='" + getComments() + "'" +
            ", data='" + getData() + "'" +
            "}";
    }
}
