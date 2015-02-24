package com.excilys.formation.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.persistence.CompanyDAO;
import com.excilys.formation.cdb.persistence.ConnectionDAO;

public class CompanyService {

   private  CompanyDAO companyDAO;
   
   public CompanyService(){
       super();
       this.companyDAO = new CompanyDAO(ConnectionDAO.getInstance());
   }
   
   public List<Company> findAll(){
       return companyDAO.findAll();
   }
}
