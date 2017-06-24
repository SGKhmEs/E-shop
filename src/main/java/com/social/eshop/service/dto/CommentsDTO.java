package com.social.eshop.service.dto;

import com.social.eshop.service.mapper.AutoMapping;

import java.time.ZonedDateTime;
import java.util.List;

public class CommentsDTO implements AutoMapping {

    private List<String> comments;
    private ZonedDateTime date;
    private CustomerDTO customerDTO;

    public CommentsDTO() {}

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
            "comments=" + comments +
            ", date=" + date +
            ", customerDTO=" + customerDTO +
            '}';
    }
}
