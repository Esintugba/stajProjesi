package org.proje.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "t_product")
public class Product extends BaseEntity {


    @Column(name = "product_code")
    private Long productCode;
    @Column(name = "product_name")
    private String name;
    @Column(name = "categories")
    private String categories;
    @Column(name = "explanation")
    private String explanation;
    @Column(name = "price")
    private Long price;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public Long getProductCode() {
        return productCode;
    }

    public void setProductCode(Long productCode) {
        this.productCode = productCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", productCode=" + productCode +
                ", name='" + name + '\'' +
                ", categories='" + categories + '\'' +
                ", explanation='" + explanation + '\'' +
                ", price=" + price +
                ", company=" + company +
                '}';
    }
}
