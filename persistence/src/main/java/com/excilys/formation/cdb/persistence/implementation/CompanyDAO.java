package com.excilys.formation.cdb.persistence.implementation;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.ICompanyDAO;

@Repository
public class CompanyDAO implements ICompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource ds) {
	dataSource = ds;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.ICompanyDAO#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<Company> findAll() {
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(Company.class).addOrder(Order.asc("name"));
	return crit.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.ICompanyDAO#delete(java.lang.Long)
     */
    @Override
    public void delete(Long id) {
	Company company = (Company) sessionFactory.getCurrentSession().load(Company.class, id);
	sessionFactory.getCurrentSession().delete(company);
    }

    @Override
    public Company find(long id) {
	return (Company) sessionFactory.getCurrentSession().get(Company.class,id);
    }

}