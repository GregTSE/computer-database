package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;

@Path("/company")
@Component
public class CompanyWebService {

    @Autowired
    ICompanyService companyService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Company> getAll(@PathParam("param") String msg) {
	List<Company> companies = companyService.findAll();
	return companies;
    }

    public void delete(Long id) {
	companyService.delete(id);
    }

    public Company find(Long id) {
	return companyService.find(id);
    }

}