package com.excilys.formation.cdb.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.excilys.formation.cdb.persistence.implementation.UserDAO;

@Service("userService")
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    @Autowired
    private UserDAO userDao;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
	com.excilys.formation.cdb.model.User user = userDao.findByUserName(username);
	List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());
	return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.excilys.formation.cdb.model.User user, List<GrantedAuthority> authorities) {
	return new User(user.getName(), user.getPassword(), true,
		true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(int role) {
	
	Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	switch(role) {
	case 1 :
	    setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    break;
	 default :
	     setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
	}
	    
	List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
		setAuths);
	return Result;
    }
}
