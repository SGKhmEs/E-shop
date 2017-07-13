package com.social.eshop.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CustomerAccount entity.
 */
public class CustomerAccountDTO implements Serializable {

    private Long id;

    private LocalDate createdAt;

    private Long userId;

    private String userLogin;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CustomerAccountDTO customerAccountDTO = (CustomerAccountDTO) o;
        if(customerAccountDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerAccountDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerAccountDTO{" +
            "id=" + getId() +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
