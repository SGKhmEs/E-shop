package com.social.eshop.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Bucket entity.
 */
public class BucketDTO implements Serializable {

    private Long id;

    private ZonedDateTime data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public void setData(ZonedDateTime data) {
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

        BucketDTO bucketDTO = (BucketDTO) o;
        if(bucketDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bucketDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BucketDTO{" +
            "id=" + getId() +
            ", data='" + getData() + "'" +
            "}";
    }
}
