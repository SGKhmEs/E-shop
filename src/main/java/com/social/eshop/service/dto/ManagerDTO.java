package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Roles;

/**
 * A DTO for the Manager entity.
 */
public class ManagerDTO implements Serializable {

    private Long id;

    private Roles roles;

    private Long loginOptionsId;

    private Long personalInfoId;

    private Long avatarId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Long getLoginOptionsId() {
        return loginOptionsId;
    }

    public void setLoginOptionsId(Long loginOptionsId) {
        this.loginOptionsId = loginOptionsId;
    }

    public Long getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Long personalInformationId) {
        this.personalInfoId = personalInformationId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ManagerDTO managerDTO = (ManagerDTO) o;
        if(managerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), managerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ManagerDTO{" +
            "id=" + getId() +
            ", roles='" + getRoles() + "'" +
            "}";
    }
}
