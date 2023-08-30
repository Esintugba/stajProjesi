package org.proje.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "t_customer")
public class Customer extends BaseEntity {

    @NotEmpty
    @Column(name = "tc")
    private Long tc;
    @NotEmpty
    @Column(name = "customer_number")
    private Long customerNumber;
    @NotEmpty
    @Column(name="first_name")
    private String firstName;
    @NotEmpty
    @Column(name="last_name")
    private String lastName;
    @NotEmpty
    @Column(name = "gender")
    private String gender;
    @NotEmpty
    @Column(name = "e_mail")
    private String eMail;

    public Long getTc() {
        return tc;
    }

    public void setTc(Long tc) {
        this.tc = tc;
    }

    public Long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + getId() +
                ", tc=" + tc +
                ", customerNumber=" + customerNumber +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
