package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.cdb.model.Company;

public class MapperCompanyDAO implements RowMapper<Company> {

    public MapperCompanyDAO() {
	super();
    }

    @Override
    public Company mapRow(ResultSet results, int rowNum) throws SQLException {
	long id = results.getLong(1);
	String name = results.getString(2);
	
	return new Company(id, name);
    }

}
