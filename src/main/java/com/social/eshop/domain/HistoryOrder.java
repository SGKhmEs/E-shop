package com.social.eshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.social.eshop.domain.enumeration.Status;

/**
 * A HistoryOrder.
 */
@Entity
@Table(name = "history_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "historyorder")
public class HistoryOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "jhi_date")
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @Column(name = "sum")
    private Double sum;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "consignment_note")
    private String consignmentNote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public HistoryOrder orderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
        return this;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public HistoryOrder date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public HistoryOrder count(Integer count) {
        this.count = count;
        return this;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public HistoryOrder sum(Double sum) {
        this.sum = sum;
        return this;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Status getStatus() {
        return status;
    }

    public HistoryOrder status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getConsignmentNote() {
        return consignmentNote;
    }

    public HistoryOrder consignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
        return this;
    }

    public void setConsignmentNote(String consignmentNote) {
        this.consignmentNote = consignmentNote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HistoryOrder historyOrder = (HistoryOrder) o;
        if (historyOrder.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historyOrder.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoryOrder{" +
            "id=" + getId() +
            ", orderNumber='" + getOrderNumber() + "'" +
            ", date='" + getDate() + "'" +
            ", count='" + getCount() + "'" +
            ", sum='" + getSum() + "'" +
            ", status='" + getStatus() + "'" +
            ", consignmentNote='" + getConsignmentNote() + "'" +
            "}";
    }
}
