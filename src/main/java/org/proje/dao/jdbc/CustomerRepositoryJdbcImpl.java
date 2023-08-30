package org.proje.dao.jdbc;

import org.proje.dao.CustomerRepository;
import org.proje.model.Company;
import org.proje.model.Customer;
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
public class CustomerRepositoryJdbcImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Customer> rowMapper=new RowMapper<Customer>() {

        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getLong("id"));
            customer.setTc(rs.getLong("tc"));
            customer.setCustomerNumber(rs.getLong("customer_number"));
            customer.setFirstName(rs.getString("first_name"));
            customer.setLastName(rs.getString("last_name"));
            customer.setGender(rs.getString("gender"));
            customer.seteMail(rs.getString("e_mail"));
            return customer;
        }
    };

    @Override
    public List<Customer> findAll() {
        String sql="Select id,tc,customer_number" +
                ",first_name,last_name,gender," +
                "e_mail from t_customer";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Customer findById(Long id) {
        String sql="Select id,tc,customer_number," +
                "first_name,last_name,gender," +
                "e_mail from t_customer where id =?";
        return jdbcTemplate.queryForObject(sql,rowMapper,id);
    }

    @Override
    public List<Customer> findByLastName(String lastName) {
        String sql="Select id,tc,customer_number," +
                "first_name,last_name,gender," +
                "e_mail from t_customer where last_name like?";
        return jdbcTemplate.query(sql,rowMapper,"%"+lastName + "%");
    }

    @Override
    public void create(Customer customer) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        PreparedStatementCreator psc=new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt=con.prepareStatement(
                        "insert into  t_customer(id,tc,customer_number,first_name,last_name,gender,e_mail)"
                        +"values (stajprojesi_sequence.nextval,?,?,?,?,?,?)"
                );
                stmt.setLong(1,customer.getTc());
                stmt.setLong(2,customer.getCustomerNumber());
                stmt.setString(3,customer.getFirstName());
                stmt.setString(4,customer.getLastName());
                stmt.setString(5,customer.getGender());
                stmt.setString(6,customer.geteMail());
                return stmt;
            }
        };
        int count= jdbcTemplate.update(psc,keyHolder);
        if (count!=1){
            throw new RuntimeException("Unable to create customer:"+customer);
        }
        customer.setId((Long) keyHolder.getKey());
    }

    @Override
    public Customer update(Customer customer) {
        int count= jdbcTemplate.update("update t_customer"
        +"set tc=?,customer_number=?,first_name=?,last_name=?,gender=?,e_mail=?"
        +"where id=?",customer.getTc(),customer.getCustomerNumber(),customer.getFirstName(),customer.getLastName(),customer.getGender(),customer.geteMail(),customer.getId());
        if (count!=1){
            throw new RuntimeException("Unable to update customer:"+customer);
        }
        return customer;
    }

    @Override
    public void delete(Long id) {
        String sql="delete from t_customer where id=?";
        jdbcTemplate.update(sql,id);
    }
}
