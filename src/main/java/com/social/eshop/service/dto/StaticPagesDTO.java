package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the StaticPages entity.
 */
public class StaticPagesDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StaticPagesDTO staticPagesDTO = (StaticPagesDTO) o;
        if(staticPagesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staticPagesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StaticPagesDTO{" +
            "id=" + getId() +
            "}";
    }
}
