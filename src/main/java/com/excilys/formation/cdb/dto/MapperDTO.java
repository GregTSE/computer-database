package com.excilys.formation.cdb.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.cdb.model.Computer;

public class MapperDTO {

    public MapperDTO() {
	super();
    }
    
    public static ComputerDTO ComputerToDTO(Computer computer) {
	String introduced = (computer.getDateIntroduced() != null) ? computer.getDateIntroduced().toString() : "" ;
	String discontinued = (computer.getDateDiscontinued() != null) ? computer.getDateDiscontinued().toString() : "" ;
	return new ComputerDTO(computer.getId(), computer.getName(), introduced, discontinued, computer.getCompany());
    }
    
//    public dtoToComputer(ComputerDTO cDTO) {
//	
//	LocalDate introduced = LocalDate.parse(cDTO.getDateIntroduced());
//	LocalDate discontinued = LocalDate.parse(cDTO.getDateDiscontinued());
//	return new Computer(cDTO.getId(), cDTO.getName(), new cDTO.getDateIntroduced())
//    }

}
