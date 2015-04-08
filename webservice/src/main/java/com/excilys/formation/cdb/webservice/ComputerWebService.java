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

@Path("/computer")
@Component
public class ComputerWebService {

    @Autowired
    IComputerService computerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ComputerDTO> getAll() {
	List<ComputerDTO> computers = MapperDTO.computersToDTO(computerService.findAll());
	return computers;
    }
    
    
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(ComputerDTO computerDTO){
	computerService.insert(MapperDTO.dtoToComputer(computerDTO));
    }
    
    @GET
    @Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public ComputerDTO get(@PathParam("param") long id) {
	ComputerDTO computerDTO = MapperDTO.computerToDTO(computerService.find(id));
	return computerDTO;
    }


}
