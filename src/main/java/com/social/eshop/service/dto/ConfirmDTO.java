package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Payment;

/**
 * A DTO for the Confirm entity.
 */
public class ConfirmDTO implements Serializable {

    private Long id;

    private Payment pay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payment getPay() {
        return pay;
    }

    public void setPay(Payment pay) {
        this.pay = pay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConfirmDTO confirmDTO = (ConfirmDTO) o;
        if(confirmDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), confirmDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConfirmDTO{" +
            "id=" + getId() +
            ", pay='" + getPay() + "'" +
            "}";
    }
}
