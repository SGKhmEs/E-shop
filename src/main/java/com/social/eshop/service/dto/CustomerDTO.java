package com.social.eshop.service.dto;

import com.social.eshop.domain.Seen;
import com.social.eshop.domain.WishList;
import com.social.eshop.service.mapper.AutoMapping;

import java.util.List;

public class CustomerDTO implements AutoMapping {

    //private String sessionId;  // think about it
    private List<WishList> wishLists;
    private List<Seen> seens;
    private CustomerRoomDTO customerRoomDTO;

    public CustomerDTO() { }

    public CustomerDTO(List<WishList> wishLists, List<Seen> seens, CustomerRoomDTO customerRoomDTO) {
        this.wishLists = wishLists;
        this.seens = seens;
        this.customerRoomDTO = customerRoomDTO;
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

    public CustomerRoomDTO getCustomerRoomDTO() {
        return customerRoomDTO;
    }

    public void setCustomerRoomDTO(CustomerRoomDTO customerRoomDTO) {
        this.customerRoomDTO = customerRoomDTO;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "wishLists=" + wishLists +
            ", seens=" + seens +
            ", customerRoomDTO=" + customerRoomDTO +
            '}';
    }

}
