package com.excilys.formation.cdb.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

public class MapperDTO {

    public MapperDTO() {
	super();
    }

    /**
     * Build the DTO equivalent to the object
     * 
     * @param computer
     * @return ComputerDTO
     */
    public static ComputerDTO computerToDTO(Computer computer) {
	String introduced = (computer.getDateIntroduced() != null) ? computer.getDateIntroduced().toString() : "";
	String discontinued = (computer.getDateDiscontinued() != null) ? computer.getDateDiscontinued().toString() : "";
	String companyName = "";
	int companyId = 0;

	if (computer.getCompany() != null) {
	    companyName = computer.getCompany().getName();
	    companyId = computer.getCompany().getId().intValue();
	}

	return new ComputerDTO(computer.getId(), computer.getName(),introduced, discontinued, companyId, companyName);
    }

    public static List<ComputerDTO> computersToDTO(List<Computer> computers) {
	List<ComputerDTO> computersDTO = new ArrayList<ComputerDTO>();
	for (Computer computer : computers) {
	    computersDTO.add(computerToDTO(computer));
	}
	return computersDTO;
    }

    /**
     * Build the object equivalent to the DTO
     * 
     * @param computerDTO
     * @return computer
     */
    public static Computer dtoToComputer(ComputerDTO computerDTO) {

	Locale locale = LocaleContextHolder.getLocale();
	LocalDate introducedDate = null;
	LocalDate discontinuedDate = null;
	
	String introduced = computerDTO.getDateIntroduced();
	String discontinued = computerDTO.getDateDiscontinued();
	
	if (introduced != null) {
	    if (locale.getLanguage().equals("fr")) {
		introducedDate = LocalDate.parse(introduced,DateTimeFormatter.ofPattern("dd-MM-yyyy", locale));
		System.out.println("dto:"+introduced+"->"+introducedDate.toString());
	    } else {
		introducedDate = LocalDate.parse(introduced);
	    }
	}
	
	if (discontinued != null) {
	    if (locale.getLanguage().equals("fr")) {
		discontinuedDate = LocalDate.parse(discontinued,DateTimeFormatter.ofPattern("dd-MM-yyyy", locale));
	    } else {
		discontinuedDate = LocalDate.parse(discontinued);
	    }
	}
	    
	
	Company company = (computerDTO.getCompanyId() == 0) ? null: new Company(computerDTO.getCompanyId(),computerDTO.getCompanyName());
	System.out.println("Computer built : "+introducedDate);
	return new Computer(computerDTO.getId(), computerDTO.getName(),introducedDate, discontinuedDate, company);
    }
}
