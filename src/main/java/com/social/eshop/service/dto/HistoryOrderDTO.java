package com.social.eshop.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Status;

/**
 * A DTO for the HistoryOrder entity.
 */
public class HistoryOrderDTO implements Serializable {

    private Long id;

    private Integer orderNumber;

    private ZonedDateTime date;

    private Integer count;

    private Double sum;

    private Status status;

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

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getConsignmentNote() {
        return consignmentNote;
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

        HistoryOrderDTO historyOrderDTO = (HistoryOrderDTO) o;
        if(historyOrderDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historyOrderDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoryOrderDTO{" +
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
