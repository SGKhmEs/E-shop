package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.social.eshop.domain.enumeration.SocialConnect;

/**
 * A DTO for the CustomerRoom entity.
 */
public class CustomerRoomDTO implements Serializable {

    private Long id;

    private Boolean subScription;

    private SocialConnect sosialConnect;

    private Long personalInfoId;

    private Long wishListId;

    private Long addressId;

    private Long bucketId;

    private Long seenId;

    private Long historyOrderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isSubScription() {
        return subScription;
    }

    public void setSubScription(Boolean subScription) {
        this.subScription = subScription;
    }

    public SocialConnect getSosialConnect() {
        return sosialConnect;
    }

    public void setSosialConnect(SocialConnect sosialConnect) {
        this.sosialConnect = sosialConnect;
    }

    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long personalInformationId) {
        this.personalInfoId = personalInformationId;
    }

    public Long getWishListId() {
        return wishListId;
    }

    public void setWishListId(Long wishListId) {
        this.wishListId = wishListId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public Long getSeenId() {
        return seenId;
    }

    public void setSeenId(Long seenId) {
        this.seenId = seenId;
    }

    public Long getHistoryOrderId() {
        return historyOrderId;
    }

    public void setHistoryOrderId(Long historyOrderId) {
        this.historyOrderId = historyOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerRoomDTO customerRoomDTO = (CustomerRoomDTO) o;
        if(customerRoomDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerRoomDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerRoomDTO{" +
            "id=" + getId() +
            ", subScription='" + isSubScription() + "'" +
            ", sosialConnect='" + getSosialConnect() + "'" +
            "}";
    }
}
