package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Tags.
 */
@Entity
@Table(name = "tags")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tags")
public class Tags implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "tags")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TagForProduct> tagForProducts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Tags name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TagForProduct> getTagForProducts() {
        return tagForProducts;
    }

    public Tags tagForProducts(Set<TagForProduct> tagForProducts) {
        this.tagForProducts = tagForProducts;
        return this;
    }

    public Tags addTagForProduct(TagForProduct tagForProduct) {
        this.tagForProducts.add(tagForProduct);
        tagForProduct.setTags(this);
        return this;
    }

    public Tags removeTagForProduct(TagForProduct tagForProduct) {
        this.tagForProducts.remove(tagForProduct);
        tagForProduct.setTags(null);
        return this;
    }

    public void setTagForProducts(Set<TagForProduct> tagForProducts) {
        this.tagForProducts = tagForProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tags tags = (Tags) o;
        if (tags.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tags.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Tags{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
