package org.proje.dao.jdbc;

import org.proje.dao.CompanyRepository;
import org.proje.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
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
public class CompanyRepositoryJdbcImpl implements CompanyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Company> rowMapper=new RowMapper<Company>() {
        @Override
        public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
            Company company = new Company();
            company.setId(rs.getLong("id"));
            company.setCompanyCode(rs.getLong("company_code"));
            company.setCompanyName(rs.getString("company_name"));
            company.setAddress(rs.getString("address"));
            company.seteMail(rs.getString("e_mail"));
            return company;
        }
    };
    @Override
    public List<Company> findAll() {
        String sql="Select id,company_code,company_name,address,e_mail from t_company";
        return jdbcTemplate.query(sql,rowMapper);
    }

    @Override
    public Company findById(Long id) {
        String sql="select id,company_code,company_name,address,e_mail from t_company where id =?";
        return DataAccessUtils.singleResult(jdbcTemplate.query(sql,rowMapper,id));
    }

    @Override
    public List<Company> findByCompanyName(String companyName) {
        String sql="select id,company_code,company_name,address,e_mail from t_company where company_name like?";
        return jdbcTemplate.query(sql,rowMapper,"%"+companyName + "%");
    }

    @Override
    public void create(Company company) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        PreparedStatementCreator pcs=new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement stmt=con.prepareStatement("insert into t_company" +
                        "(id,company_code,company_name,address,e_mail)"
                        +"values (stajprojesi_sequence.netval,?,?,?,?)");
                stmt.setLong(1,company.getCompanyCode());
                stmt.setString(2,company.getCompanyName());
                stmt.setString(3,company.getAddress());
                stmt.setString(4,company.geteMail());
                return stmt;
            }
        };
        int count =jdbcTemplate.update(pcs,keyHolder);
        if(count!=1){
            throw new RuntimeException("Unable to create company:"+company);
        }
        company.setId((Long) keyHolder.getKey());
    }

    @Override
    public Company update(Company company) {
        int count=jdbcTemplate.update("update t_company"+"set company_code=?,company_name=?,address=?,e_mail=?"+"where id=?",company.getCompanyCode(),company.getCompanyName(),company.getAddress(),company.geteMail(),company.getId());
        if (count!=1){
            throw new RuntimeException("Unable to update company:"+company);
        }
        return company;
    }

    @Override
    public void delete(Long id) {
        String sql="delete from t_company where id=?";
        jdbcTemplate.update(sql,id);
    }
}
