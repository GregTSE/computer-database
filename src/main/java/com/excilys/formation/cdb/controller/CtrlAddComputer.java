package com.excilys.formation.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;

@Controller
@RequestMapping("/addComputer")
public class CtrlAddComputer {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IComputerService computerService;

    public CtrlAddComputer() {
	super();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String displayCompanies(ModelMap model) {
	List<Company> companies = new ArrayList<Company>();
	companies = companyService.findAll();
	model.addAttribute("companies", companies);
	model.addAttribute("computerDTO", new ComputerDTO());
	return "addComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String addComputer(ModelMap model,
	    @Valid ComputerDTO computerDTO, BindingResult bindingResult) {

	if (bindingResult.hasErrors()) {
	    model.addAttribute("computerDTO", computerDTO);
	    return "form";
	} else {

	    // Insert in the database
	    computerService.insert(MapperDTO.dtoToComputer(computerDTO));

	    // Redirection
	    Page page = new Page();
	    page.setComputersDTO(MapperDTO.computersToDTO(computerService
		    .search("", 1, 10)));// computerService.findAll()));

	    model.addAttribute("page", page);
	    model.addAttribute("computersFound", computerService.count(""));
	    System.out.println("END POST ADD");

	    return "dashboard";
	}
    }

}
