package org.proje.dao.jdbc;

import org.proje.dao.ProductRepository;
import org.proje.model.Company;
import org.proje.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepositoryJdbcImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private RowMapper<Product> rowMapper=new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product=new Product();
            Company company=new Company();
            product.setId(rs.getLong("id"));
            product.setProductCode(rs.getLong("product_code"));
            product.setName(rs.getString("product_name"));
            product.setCategories(rs.getString("categories"));
            product.setExplanation(rs.getString("explanation"));
            product.setPrice(rs.getLong("price"));
            product.setCompany(product.getCompany());
            company.setId(rs.getLong("company_id"));
            product.setCompany(company);
            return product;
        }
    };

    @Override
    public List<Product> findAll() {
        String sql="Select id,product_code,product_name,categories,explanation,price,company_id from t_product";
        return jdbcTemplate.query(sql,rowMapper);
    }
    @Override
    public Product findById(Long id) {
        String sql="Select id,product_code,product_name,categories,explanation,price,company_id from t_product where id =?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    @Override
    public List<Product> findByProductCode(Long productCode) {
        String sql="Select id,product_code,product_name,categories,explanation,price,company_id from t_product where product_code like?";
        return jdbcTemplate.query(sql,rowMapper,"%"+productCode + "%");
    }

    @Override
    public void create(Product product) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        PreparedStatementCreator psc=new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt=con.prepareStatement(
                        "insert into t_product(id,product_code,product_name,categories,explanation,price,company_id)"+"values (stajprojesi_sequence.nextval,?,?,?,?,?,?)");
                stmt.setLong(1,product.getProductCode());
                stmt.setString(2,product.getName());
                stmt.setString(3,product.getCategories());
                stmt.setString(4,product.getExplanation());
                stmt.setLong(5,product.getPrice());
                stmt.setLong(6,product.getCompany().getId());
                return stmt;
            }
        };
        int count = jdbcTemplate.update(psc,keyHolder);
        if(count!=1){
            throw new RuntimeException("Unable to create product:"+product);
        }
        product.setId((Long) keyHolder.getKey());
    }

    @Override
    public Product update(Product product) {
        int count= jdbcTemplate.update("update t_product"
                +"set product_code=?,product_name=?,categories=?,explanation=?,price=?,company_id?"
                +"where id=?",product.getProductCode(),product.getName(),product.getCategories(),
                product.getExplanation(),product.getPrice(),product.getCompany(),product.getId());
        if (count!=1){
            throw new RuntimeException("Unable to update product:"+product);
        }
        return product;
    }

    @Override
    public void delete(Long id) {
        String sql="delete from t_product where id=?";
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void deleteByCompanyId(Long companyId) {
        String sql="delete from t_product where company_id=?";
        jdbcTemplate.update(sql,companyId);
    }
}
