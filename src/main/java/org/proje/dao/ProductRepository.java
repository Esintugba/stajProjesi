package org.proje.dao;

import org.proje.model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(Long id);
    List<Product> findByProductCode(Long productCode);
    void create(Product product);
    Product update(Product product);
    void delete(Long id);
    void deleteByCompanyId(Long companyId);
}
