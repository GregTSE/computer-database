package com.excilys.formation.cdb.persistence.implementation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;

@Repository
public class ComputerDAO implements IComputerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    final Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#find(int)
     */
    @Override
    public Computer find(long id) {
	return (Computer) sessionFactory.getCurrentSession().get(
		Computer.class, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll()
     */
    @Override
    public List<Computer> findAll() {
	Criteria crit = sessionFactory.getCurrentSession()
		.createCriteria(Computer.class).addOrder(Order.asc("name"));
	return crit.list();
    }

    /*** METHODS FOR WEB-UI ***/

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#findAll(int, int)
     */
    @Override
    public List<Computer> findAll(int num, int offset) {
	Criteria crit = sessionFactory.getCurrentSession()
		.createCriteria(Computer.class).addOrder(Order.asc("name"));
	crit.setMaxResults(num);
	crit.setFirstResult(offset);
	return crit.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#create(java.lang.String
     * , java.lang.String, java.lang.String,
     * com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void create(Computer computer) {
	sessionFactory.getCurrentSession().save(computer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#update(com.excilys
     * .formation.cdb.model.Computer)
     */
    @Override
    public void update(Computer computer) {
	sessionFactory.getCurrentSession().update(computer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.excilys.formation.cdb.persistence.IComputerDAO#delete(long)
     */
    @Override
    public void delete(long id) {
	Computer computer = (Computer) sessionFactory.getCurrentSession().load(
		Computer.class, id);
	sessionFactory.getCurrentSession().delete(computer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#search(java.lang.String
     * , int, int)
     */
    @Override
    public List<Computer> search(String str, int num, int offset, String sort) {

	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
		Computer.class);
	if (sort.equalsIgnoreCase("DESC")) {
	    crit.addOrder(Order.desc("name"));
	} else {
	    crit.addOrder(Order.asc("name"));
	}
	crit.add(Restrictions.like("name", str + "%"));
	crit.setFirstResult(num);
	crit.setMaxResults(offset);
	return crit.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#count(java.lang.String
     * )
     */
    @Override
    public int count(String name) {
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(
		Computer.class);
	crit.add(Restrictions.like("name", name + "%"));
	return (int) (long) crit.setProjection(Projections.rowCount())
		.uniqueResult();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#findByCompany(java
     * .lang.Long)
     */
    @Override
    public List<Long> findByCompany(Long companyId) {
	Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Computer.class);
	criteria.add(Restrictions.eq("company", companyId));
	System.out.println(criteria.list().toString());
	return criteria.list();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#deleteByCompany(java
     * .lang.Long)
     */
    @Override
    public void deleteByCompany(Long id) {
	List<Long> computersId = findByCompany(id);
	// for (Long computerId : computersId) {
	// delete(computerId);
	// logger.info("computer(" + computerId + ") removed");
    }
}
