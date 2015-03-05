package com.excilys.formation.cdb.persistence;

import java.sql.Connection;
import java.util.List;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyDAO {

    /**
     * Find all companies
     * @return companies list
     */

    public abstract void delete(Long id, Connection connection);

    public abstract List<Company> findAll(Connection connection);

}