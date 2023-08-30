package org.proje.service;

import org.proje.exception.ProductNotFoundException;
import org.proje.model.Product;

import javax.validation.Valid;
import java.util.List;

public interface ProductService {

    List<Product> findProducts();
    List<Product> findProducts(Long productCode);
    Product findProduct(Long id) throws ProductNotFoundException;
    void createProduct(@Valid Product product);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
