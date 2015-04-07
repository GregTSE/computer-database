package com.excilys.formation.cdb.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.service.ICompanyService;

@Path("/hello")
@Component
public class WebServiceHello {

    @Autowired
    private ICompanyService serv;
    
    public WebServiceHello() {
    }

    @GET
    @Path("/{param}")
    public Response getMessage(@PathParam("param") String message) {
	
	String output = "Jersey say Hello World!!! : " + message + serv.findAll().toString();;
	return Response.status(200).entity(output).build();
    }
}
