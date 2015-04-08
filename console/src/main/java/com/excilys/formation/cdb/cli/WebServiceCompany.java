package com.excilys.formation.cdb.cli;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.model.Company;

@Component
public class WebServiceCompany {

    private Client client;
    private WebTarget webTarget;
    public static final String URL = "http://localhost:8080/ComputerDatabase/webservice/company";
   
    public WebServiceCompany() {
	this.client = ClientBuilder.newBuilder().register(JacksonFeature.class).build();
	this.webTarget = client.target(URL);
    }
    
    public List<Company> getAll() {
	return   webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Company>>() {});
    }

}
