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
 * Client part of the web service for Computers
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
     * 
     * @return List of data transfert computers
     */
    public List<ComputerDTO> getAll() {
	return webTarget.request(MediaType.APPLICATION_JSON).get(
		new GenericType<List<ComputerDTO>>() {
		});
    }

    /**
     * Get a computer by ID
     * @param id
     * @return
     */
    public ComputerDTO get(String id) {
	return webTarget.path(id).request(MediaType.APPLICATION_JSON)
		.get(new GenericType<ComputerDTO>() {
		});
    }

    /**
     * 
     * @param computerDTO
     */
    public void add(ComputerDTO computerDTO) {
	webTarget.path("/add").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    /**
     * 
     * @param computerDTO
     */
    public void update(ComputerDTO computerDTO) {
	webTarget.path("/update").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    /**
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