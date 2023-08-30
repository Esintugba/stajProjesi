package org.proje;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="stajprojesi")
public class ProjeProperties {

    private boolean displayCustomersWithProducts = false;

    public boolean isDisplayCustomersWithProducts() {
        return displayCustomersWithProducts;
    }

    public void setDisplayCustomersWithProducts(boolean displayCustomersWithProducts) {
        this.displayCustomersWithProducts = displayCustomersWithProducts;
    }
}
