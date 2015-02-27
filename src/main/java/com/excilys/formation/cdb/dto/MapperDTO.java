package com.excilys.formation.cdb.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.cdb.model.Computer;

public class MapperDTO {

    public MapperDTO() {
	super();
    }
    
    public ComputerDTO ComputerToDTO(Computer computer) {
	return new ComputerDTO(computer.getId(), computer.getName(), computer.getDateIntroduced().toString(), computer.getDateDiscontinued().toString(), computer.getCompany());
    }
    
//    public dtoToComputer(ComputerDTO cDTO) {
//	
//	LocalDate introduced = LocalDate.parse(cDTO.getDateIntroduced());
//	LocalDate discontinued = LocalDate.parse(cDTO.getDateDiscontinued());
//	return new Computer(cDTO.getId(), cDTO.getName(), new cDTO.getDateIntroduced())
//    }

}
