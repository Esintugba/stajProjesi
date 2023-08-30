package org.proje.dao.jpa;

import org.proje.dao.ProductRepository;
import org.proje.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository("productRepository")
public class ProductRepositoryJpaImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> findAll() {
        return entityManager.createQuery("from Product ", Product.class).getResultList();
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class,id);
    }

    @Override
    public List<Product> findByProductCode(Long productCode) {
        return entityManager.createQuery("from Product where productCode=:productCode", Product.class)
                .setParameter("productCode",productCode).getResultList();
    }

    @Override
    public void create(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Product update(Product product) {
        return entityManager.merge(product);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Product.class,id));
    }

    @Override
    public void deleteByCompanyId(Long companyId) {
        entityManager.createQuery("delete from Product where company.id = :companyId").setParameter("companyId", companyId)
                .executeUpdate();
    }
}
