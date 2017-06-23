package com.social.eshop.service.dto;

import com.social.eshop.domain.*;

import java.util.List;

public class CustomerDTO {
    private PersonalInformation personalInformation;
    private Avatar avatar;
    private Address address;
    private List<WishList> wishLists;
    private List<Seen> seens;


    public CustomerDTO() {
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "personalInformation=" + personalInformation +
            ", avatar=" + avatar +
            ", address=" + address +
            ", wishLists=" + wishLists +
            ", seens=" + seens +
            '}';
    }
}
