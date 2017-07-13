package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Avatar entity.
 */
public class AvatarDTO implements Serializable {

    private Long id;

    @Lob
    private byte[] usersImage;
    private String usersImageContentType;

    private Long customerId;

    private Long managerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getUsersImage() {
        return usersImage;
    }

    public void setUsersImage(byte[] usersImage) {
        this.usersImage = usersImage;
    }

    public String getUsersImageContentType() {
        return usersImageContentType;
    }

    public void setUsersImageContentType(String usersImageContentType) {
        this.usersImageContentType = usersImageContentType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AvatarDTO avatarDTO = (AvatarDTO) o;
        if(avatarDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avatarDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AvatarDTO{" +
            "id=" + getId() +
            ", usersImage='" + getUsersImage() + "'" +
            "}";
    }
}
