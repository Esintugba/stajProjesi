package org.proje.service;

import org.proje.dao.ProductRepository;
import org.proje.exception.CompanyNotFoundException;
import org.proje.exception.ProductNotFoundException;
import org.proje.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Product> findProducts(Long productCode) {
        return productRepository.findByProductCode(productCode);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    @Secured(value = {"ROLE_USER","ROLE_EDITOR"})
    public Product findProduct(Long id) throws ProductNotFoundException {
        Product product=productRepository.findById(id);
        if (product==null){
            throw new ProductNotFoundException("Product not found with id:"+id);
        }
        return product;
    }

    @Override
    @CacheEvict(cacheNames ="allProducts",allEntries = true)
    public void createProduct(@Valid Product product) {
        productRepository.create(product);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("k@s");
        msg.setTo("m@y");
        msg.setSubject("Product created!");
        msg.setText("Product entity with id :" + product.getId() + " created successfully.");

        javaMailSender.send(msg);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.update(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteByCompanyId(id);

    }
}
