package com.excilys.formation.cdb.service.implementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ConnectionDAO;
import com.excilys.formation.cdb.service.ICompanyService;

public abstract class AbsCompanyService implements ICompanyService {

    @Autowired
    private ConnectionDAO connectionDAO;
    
    public AbsCompanyService() {
    }

    @Override
    public final List<Company> findAll() {
	List<Company> companies = null;
	companies = findAllAbs();
	return companies;
    }

    @Override
    public final void delete(Long id) {
	Logger logger = LoggerFactory.getLogger(AbsCompanyService.class);
	Connection connection = null;
	try {
	    connection = getConnection();
	    connection.setAutoCommit(false);
	    deleteAbs(id);
	    connection.commit();
	} catch (SQLException e) {
	    try {
		logger.error("SQL Exception (transaction)");
		connection.rollback();
	    } catch (SQLException e1) {
		logger.error("SQL Exception (rollback)");
		e1.printStackTrace();
	    }
	    e.printStackTrace();
	} finally {
	    closeConnection();
	}
    }

    public abstract List<Company> findAllAbs();

    public abstract void deleteAbs(Long id);

    private Connection getConnection() {
	return connectionDAO.getConnection();
    }

    private void closeConnection() {
	connectionDAO.closeConnection();
    }
    

}
