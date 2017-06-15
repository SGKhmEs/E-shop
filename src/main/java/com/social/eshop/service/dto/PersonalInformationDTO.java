package com.social.eshop.service.dto;

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
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "PersonalInformationDTO{" +
            "lastName='" + lastName + '\'' +
            '}';
    }
}
