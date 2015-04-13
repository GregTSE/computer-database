package com.excilys.formation.cdb.persistence.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.formation.cdb.model.User;
import com.excilys.formation.cdb.persistence.IUserDAO;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    public UserDAO() {
	super();
    }

    @Override
    public User findByUserName(String username) {	
	return (User) sessionFactory.getCurrentSession().get(User.class,username);
    }

}
