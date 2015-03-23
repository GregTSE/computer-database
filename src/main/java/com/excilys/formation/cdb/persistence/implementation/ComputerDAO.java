package com.excilys.formation.cdb.persistence.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.persistence.IComputerDAO;

@Repository
public class ComputerDAO implements IComputerDAO {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private DataSource dataSource;

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
    @SuppressWarnings("unchecked")
    @Override
    public List<Computer> findAll() {
	Criteria crit = sessionFactory.getCurrentSession()
		.createCriteria(Computer.class).addOrder(Order.asc("name"));
	return crit.list();
    }

    /*** METHODS FOR WEB-UI ***/

    @SuppressWarnings("unchecked")
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
	Computer computer = (Computer) sessionFactory.getCurrentSession().load(Computer.class, id);
	sessionFactory.getCurrentSession().delete(computer);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#search(java.lang.String
     * , int, int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Computer> search(String str, int num, int offset) {
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(Computer.class).addOrder(Order.asc("name"));;
	crit.add(Restrictions.like("name", str+"%"));
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
	Criteria crit = sessionFactory.getCurrentSession().createCriteria(Computer.class);
	crit.add(Restrictions.like("name", name+"%"));
		return (int) (long)crit.setProjection(Projections.rowCount()).uniqueResult();
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

	List<Long> computersId = new ArrayList<Long>();
	String query = "SELECT id FROM computer WHERE company_id = ?";
	JdbcTemplate find = new JdbcTemplate(dataSource);
	List<Map<String, Object>> rows = find.queryForList(query,
		new Object[] { companyId });

	for (Map<String, Object> row : rows) {
	    computersId.add(Long.parseLong(row.get(0).toString()));
	}

	return computersId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.excilys.formation.cdb.persistence.IComputerDAO#deleteByCompany(java
     * .lang.Long)
     */
    @Override
    @Transactional
    public void deleteByCompany(Long id) {
	List<Long> computersId = findByCompany(id);
	for (Long computerId : computersId) {
	    delete(computerId);
	    logger.info("computer(" + computerId + ") removed");
	}
    }
}
