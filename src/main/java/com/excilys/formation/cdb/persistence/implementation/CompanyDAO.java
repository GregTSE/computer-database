package com.excilys.formation.cdb.persistence.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;


@Repository
public class CompanyDAO implements ICompanyDAO {
    
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource ds) {
      dataSource = ds;
    }
  
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#findAll()
     */
    @Override
    public List<Company> findAll() {
	
	List<Company> companies = new ArrayList<Company>();
	String query = "SELECT * FROM company";
	
	JdbcTemplate selectAll = new JdbcTemplate(dataSource);
	
	List<Map<String, Object>> rows = selectAll.queryForList(query);
	for (Map<String, Object> row : rows) {
		Company company = new Company();
		company.setId((Integer.parseInt(row.get("ID").toString())));
		company.setName((String)row.get("NAME"));
		
		companies.add(company);
	}
	return companies;
    }

    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	String query = "DELETE FROM company WHERE id=?";
	JdbcTemplate delete = new JdbcTemplate(dataSource);
	Object[] params = { id };
	delete.update(query, params);
    }
    
}