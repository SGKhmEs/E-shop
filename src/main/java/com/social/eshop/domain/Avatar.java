package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Avatar.
 */
@Entity
@Table(name = "avatar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "avatar")
public class Avatar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Column(name = "users_image")
    private byte[] usersImage;

    @Column(name = "users_image_content_type")
    private String usersImageContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getUsersImage() {
        return usersImage;
    }

    public Avatar usersImage(byte[] usersImage) {
        this.usersImage = usersImage;
        return this;
    }

    public void setUsersImage(byte[] usersImage) {
        this.usersImage = usersImage;
    }

    public String getUsersImageContentType() {
        return usersImageContentType;
    }

    public Avatar usersImageContentType(String usersImageContentType) {
        this.usersImageContentType = usersImageContentType;
        return this;
    }

    public void setUsersImageContentType(String usersImageContentType) {
        this.usersImageContentType = usersImageContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Avatar avatar = (Avatar) o;
        if (avatar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), avatar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Avatar{" +
            "id=" + getId() +
            ", usersImage='" + getUsersImage() + "'" +
            ", usersImageContentType='" + usersImageContentType + "'" +
            "}";
    }
}
