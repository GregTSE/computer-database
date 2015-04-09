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

@Component
public class WebServiceComputer {

    private Client client;
    private WebTarget webTarget;
    public static final String URL = "http://localhost:8080/ComputerDatabase/webservice/computer";

    public WebServiceComputer() {
	this.client = ClientBuilder.newBuilder().register(JacksonFeature.class)
		.build();
	this.webTarget = client.target(URL);
    }

    public List<ComputerDTO> getAll() {
	return webTarget.request(MediaType.APPLICATION_JSON).get(
		new GenericType<List<ComputerDTO>>() {
		});
    }

    public ComputerDTO get(String id) {
	return webTarget.path(id).request(MediaType.APPLICATION_JSON)
		.get(new GenericType<ComputerDTO>() {
		});
    }

    public void add(ComputerDTO computerDTO) {
	webTarget.path("/add").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    public void update(ComputerDTO computerDTO) {
	webTarget.path("/update").request()
		.post(Entity.entity(computerDTO, "application/json"));
    }

    public String delete(long id) {
	return webTarget.path("/delete").path(String.valueOf(id))
		.request(MediaType.APPLICATION_JSON)
		.get(new GenericType<String>() {
		});
    }

}