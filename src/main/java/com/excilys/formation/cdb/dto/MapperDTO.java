package com.excilys.formation.cdb.dto;

import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

public class MapperDTO {

    public MapperDTO() {
	super();
    }

    /**
     * Build the  DTO equivalent to the object
     * @param computer
     * @return ComputerDTO
     */
    public static ComputerDTO ComputerToDTO(Computer computer) {
	String introduced = (computer.getDateIntroduced() != null) ? computer.getDateIntroduced().toString() : "";
	String discontinued = (computer.getDateDiscontinued() != null) ? computer.getDateDiscontinued().toString() : "";
	String companyName = "";
	int companyId = 0;
	
	if (computer.getCompany() != null) {
	    companyName = computer.getCompany().getName();
	    companyId = computer.getCompany().getId().intValue();
	}

	return new ComputerDTO(computer.getId(), computer.getName(), introduced, discontinued, companyId, companyName);
    }

    /**
     * Build the object equivalent to the DTO
     * @param computerDTO
     * @return computer
     */
    public Computer dtoToComputer(ComputerDTO computerDTO) {

	String introduced = computerDTO.getDateIntroduced();
	String discontinued = computerDTO.getDateDiscontinued();

	LocalDate introducedDate = (introduced.equals("")) ? null : LocalDate.parse(introduced);
	LocalDate discontinuedDate = (introduced.equals("")) ? null : LocalDate.parse(discontinued);
	Company company = (computerDTO.getCompanyId() == 0) ? null : new Company(computerDTO.getCompanyId(), computerDTO.getCompanyName());
	return new Computer(computerDTO.getId(), computerDTO.getName(), introducedDate,discontinuedDate, company);
    }
}
