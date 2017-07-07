package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Type entity.
 */
public class TypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String behavior;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TypeDTO typeDTO = (TypeDTO) o;
        if(typeDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), typeDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TypeDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", behavior='" + getBehavior() + "'" +
            "}";
    }
}
