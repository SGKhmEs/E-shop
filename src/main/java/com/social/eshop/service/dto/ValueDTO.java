package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Value entity.
 */
public class ValueDTO implements Serializable {

    private Long id;

    @NotNull
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ValueDTO valueDTO = (ValueDTO) o;
        if(valueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), valueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ValueDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
