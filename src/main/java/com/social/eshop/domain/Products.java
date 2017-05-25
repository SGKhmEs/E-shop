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
 * A Products.
 */
@Entity
@Table(name = "products")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "products")
public class Products implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sale")
    private String sale;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "fresh")
    private Boolean fresh;

    @ManyToOne
    private WishList wishList;

    @ManyToOne
    private Seen seen;

    @ManyToOne
    private Bucket bucket;

    @OneToMany(mappedBy = "products")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Comments> comments = new HashSet<>();

    @OneToMany(mappedBy = "products")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Consignment> consignments = new HashSet<>();

    @ManyToOne
    private SubCategory subCategory;

    @ManyToOne
    private Media media;

    @ManyToOne
    private Tags tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Products name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSale() {
        return sale;
    }

    public Products sale(String sale) {
        this.sale = sale;
        return this;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public Double getRating() {
        return rating;
    }

    public Products rating(Double rating) {
        this.rating = rating;
        return this;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean isFresh() {
        return fresh;
    }

    public Products fresh(Boolean fresh) {
        this.fresh = fresh;
        return this;
    }

    public void setFresh(Boolean fresh) {
        this.fresh = fresh;
    }

    public WishList getWishList() {
        return wishList;
    }

    public Products wishList(WishList wishList) {
        this.wishList = wishList;
        return this;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Seen getSeen() {
        return seen;
    }

    public Products seen(Seen seen) {
        this.seen = seen;
        return this;
    }

    public void setSeen(Seen seen) {
        this.seen = seen;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public Products bucket(Bucket bucket) {
        this.bucket = bucket;
        return this;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public Set<Comments> getComments() {
        return comments;
    }

    public Products comments(Set<Comments> comments) {
        this.comments = comments;
        return this;
    }

    public Products addComments(Comments comments) {
        this.comments.add(comments);
        comments.setProducts(this);
        return this;
    }

    public Products removeComments(Comments comments) {
        this.comments.remove(comments);
        comments.setProducts(null);
        return this;
    }

    public void setComments(Set<Comments> comments) {
        this.comments = comments;
    }

    public Set<Consignment> getConsignments() {
        return consignments;
    }

    public Products consignments(Set<Consignment> consignments) {
        this.consignments = consignments;
        return this;
    }

    public Products addConsignment(Consignment consignment) {
        this.consignments.add(consignment);
        consignment.setProducts(this);
        return this;
    }

    public Products removeConsignment(Consignment consignment) {
        this.consignments.remove(consignment);
        consignment.setProducts(null);
        return this;
    }

    public void setConsignments(Set<Consignment> consignments) {
        this.consignments = consignments;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Products subCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public Media getMedia() {
        return media;
    }

    public Products media(Media media) {
        this.media = media;
        return this;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Tags getTags() {
        return tags;
    }

    public Products tags(Tags tags) {
        this.tags = tags;
        return this;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Products products = (Products) o;
        if (products.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), products.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Products{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", sale='" + getSale() + "'" +
            ", rating='" + getRating() + "'" +
            ", fresh='" + isFresh() + "'" +
            "}";
    }
}
