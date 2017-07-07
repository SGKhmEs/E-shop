package com.social.eshop.service.dto;

import com.social.eshop.domain.Address;
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.domain.enumeration.SocialConnect;
import com.social.eshop.service.mapper.AutoMapping;

public class CustomerRoomDTO implements AutoMapping {

    private boolean subscription;
    // private SocialConnect socialConnect; // think about it
    private Address address;
    private PersonalInformation personalInformation;

    public CustomerRoomDTO() { }

    public CustomerRoomDTO(boolean subscription, Address address, PersonalInformation personalInformation) {
        this.subscription = subscription;
        this.address = address;
        this.personalInformation = personalInformation;
    }

    public boolean getSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }

    @Override
    public String toString() {
        return "CustomerRoomDTO{" +
            "subscription=" + subscription +
            ", address=" + address +
            ", personalInformation=" + personalInformation +
            '}';
    }
}
