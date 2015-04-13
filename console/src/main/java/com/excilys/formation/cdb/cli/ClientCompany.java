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

/**
 * Client class using the webservice for companies
 * @author Gregori T.
 */
@Component
public class ClientCompany {

    private Client client;
    private WebTarget webTarget;
  
    public static final String URL = "http://localhost:8080/ComputerDatabase/webservice/company";

    public ClientCompany() {
	this.client = ClientBuilder.newBuilder().register(JacksonFeature.class)
		.build();
	this.webTarget = client.target(URL);
    }

    /**
     * Return the list of companies
     * @return
     */
    public List<Company> getAll() {
	return webTarget.request(MediaType.APPLICATION_JSON).get(
		new GenericType<List<Company>>() {
		});
    }

    /**
     * Delete a company by ID
     * @param id
     */
    public void delete(Long id) {
	webTarget.path("/delete").path(String.valueOf(id))
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<String>() {
		});
    }

    /**
     * Return a company by ID
     * @param id
     * @return Company
     */
    public Company get(String id) {
	return webTarget.path(id).request(MediaType.APPLICATION_JSON)
		.get(new GenericType<Company>() {
		});
    }

}
