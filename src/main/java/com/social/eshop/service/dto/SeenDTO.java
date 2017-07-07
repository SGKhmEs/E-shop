package com.social.eshop.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Seen entity.
 */
public class SeenDTO implements Serializable {

    private Long id;

    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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

        SeenDTO seenDTO = (SeenDTO) o;
        if(seenDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), seenDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SeenDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
