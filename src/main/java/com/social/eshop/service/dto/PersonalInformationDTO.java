package com.social.eshop.service.dto;

<<<<<<< HEAD

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.social.eshop.domain.enumeration.Sex;

/**
 * A DTO for the PersonalInformation entity.
 */
public class PersonalInformationDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String middleName;

    private Sex sex;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    private LocalDate dateBirth;

    private Long avatarId;

    private Long addressShippingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
=======
/**
 * Created by Alexander Torchynskyi on 15.06.2017.
 */
public class PersonalInformationDTO {
    private String firstname;
    private String lastName;

    public PersonalInformationDTO() {  }

    public PersonalInformationDTO(String firstname, String lastName) {
        this.firstname = firstname;
        this.lastName = lastName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
>>>>>>> creatingServices
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

<<<<<<< HEAD
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Long getAddressShippingId() {
        return addressShippingId;
    }

    public void setAddressShippingId(Long addressShippingId) {
        this.addressShippingId = addressShippingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonalInformationDTO personalInformationDTO = (PersonalInformationDTO) o;
        if(personalInformationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personalInformationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonalInformationDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", sex='" + getSex() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateBirth='" + getDateBirth() + "'" +
            "}";
=======
    @Override
    public String toString() {
        return "PersonalInformationDTO{" +
            "lastName='" + lastName + '\'' +
            '}';
>>>>>>> creatingServices
    }
}
