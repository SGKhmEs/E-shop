package com.social.eshop.service.dto;

import com.social.eshop.domain.Address;
import com.social.eshop.domain.PersonalInformation;
import com.social.eshop.domain.enumeration.SocialConnect;
import com.social.eshop.service.mapper.AutoMapping;

public class CustomerRoomDTO implements AutoMapping {

    private boolean subscription;
    private SocialConnect socialConnect;
    private Address address;
    private PersonalInformation personalInformation;

    public CustomerRoomDTO() { }

    public boolean isSubscription() {
        return subscription;
    }

    public void setSubscription(boolean subscription) {
        this.subscription = subscription;
    }

    public SocialConnect getSocialConnect() {
        return socialConnect;
    }

    public void setSocialConnect(SocialConnect socialConnect) {
        this.socialConnect = socialConnect;
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
            ", socialConnect=" + socialConnect +
            ", address=" + address +
            ", personalInformation=" + personalInformation +
            '}';
    }

}
