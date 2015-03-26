package com.excilys.formation.cdb.persistence;

import com.excilys.formation.cdb.model.User;

public interface IUserDAO {
 	 
	User findByUserName(String username);
 
}
