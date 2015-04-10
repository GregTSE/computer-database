package com.excilys.formation.cdb.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.service.IComputerService;

/**
 * @author Gregori T.
 *
 */
@Path("/computer")
@Component
public class ComputerWebService {

    @Autowired
    IComputerService computerService;

    /**
     * Return the list of computers in json 
     * @return list of computerDTO
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComputerDTO> getAll() {
	List<ComputerDTO> computers = MapperDTO.computersToDTO(computerService
		.findAll());
	return computers;
    }

    /**
     * Add a computer in the database
     * @param computerDTO
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(ComputerDTO computerDTO) {
	computerService.insert(MapperDTO.dtoToComputer(computerDTO));
    }

    /**
     * Return a computer by ID in json 
     * @param id
     * @return computerDTO
     */
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public ComputerDTO get(@PathParam("param") long id) {
	ComputerDTO computerDTO = computerService.find(id);
	return computerDTO;
    }

    /**
     * Update a computer
     * @param computerDTO
     */
    @POST
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(ComputerDTO computerDTO) {
	computerService.update(MapperDTO.dtoToComputer(computerDTO));
    }

    /**
     * Delete a computer by ID
     * @param id
     */
    @GET
    @Path("/delete/{id}")
    @Produces("application/json")
    public String removeComputer(@PathParam("id") long id) {
	computerService.delete(id);
	return "Success";
    }

}
