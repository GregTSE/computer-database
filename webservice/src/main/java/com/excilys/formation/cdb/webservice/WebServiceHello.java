package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;

@Path("/hello")
@Produces("application/json")
@Component
public class WebServiceHello {

    @Autowired
    private ICompanyService serv;
    
    public WebServiceHello() {
    }

    @GET
    @Path("/{param}")
    public List<Company> getMessage(@PathParam("param") String message) {
	
	List<Company> output = serv.findAll();
	return output;
    }
}
