package com.excilys.formation.cdb.cli;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.ComputerDTO;

/**
 * Client class using the webservice for computers
 * 
 * @author Gregori T.
 */
@Component
public class WebServiceComputer {

    private Client client;
    private WebTarget webTarget;

    private static final String URL = "http://localhost:8080/ComputerDatabase/webservice/computer";

    public WebServiceComputer() {
	this.client = ClientBuilder.newBuilder().register(JacksonFeature.class)
		.build();
	this.webTarget = client.target(URL);
    }

    /**
     * Return the list of computers as data transfert objects
     * 
     * @return list of computerDTO
     */
    public List<ComputerDTO> getAll() {
	return webTarget.request(MediaType.APPLICATION_JSON).get(
		new GenericType<List<ComputerDTO>>() {
		});
    }

    /**
     * Get a computer by ID
     * 
     * @param id
     * @return computerDTO
     */
    public ComputerDTO get(String id) {
	return webTarget.path(id).request(MediaType.APPLICATION_JSON)
		.get(new GenericType<ComputerDTO>() {
		});
    }

    /**
     * Add a computer in the database
     * 
     * @param computerDTO
     */
    public void add(ComputerDTO computerDTO) {
	webTarget.path("/add").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    /**
     * Update a computer
     * 
     * @param computerDTO
     */
    public void update(ComputerDTO computerDTO) {
	webTarget.path("/update").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    /**
     * Delete a computer by ID
     * 
     * @param id
     * @return
     */
    public String delete(long id) {
	return webTarget.path("/delete").path(String.valueOf(id))
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<String>() {
		});
    }

}