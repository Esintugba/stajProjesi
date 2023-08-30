package org.proje.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="t_company")
@XmlRootElement
public class Company extends BaseEntity {

    @NotEmpty
    @Column(name = "company_code")
    private Long companyCode;
    @NotEmpty
    @Column(name = "company_name")
    private String companyName;
    @NotEmpty
    @Column(name="address")
    private String address;
    @NotEmpty
    @Column(name = "e_mail")
    private String eMail;
    @OneToMany(mappedBy = "company")
    private Set<Product> products=new HashSet<>();


    public Long getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Long companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    @XmlTransient
    @JsonIgnore
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + getId() +
                ", companyCode=" + companyCode +
                ", companyName='" + companyName + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}


