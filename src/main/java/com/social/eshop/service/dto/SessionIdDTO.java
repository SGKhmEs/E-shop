package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the SessionId entity.
 */
public class SessionIdDTO implements Serializable {

    private Long id;

    private Long customerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        SessionIdDTO sessionIdDTO = (SessionIdDTO) o;
        if(sessionIdDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sessionIdDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SessionIdDTO{" +
            "id=" + getId() +
            "}";
    }
}
