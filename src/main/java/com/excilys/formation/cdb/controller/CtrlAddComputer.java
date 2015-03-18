package com.excilys.formation.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.dto.ValidatorDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;

@Controller
@RequestMapping("/addComputer")
public class CtrlAddComputer {
    private Page page;
    private static final String COMPUT_NAME = "computerName";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "companyId";

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IComputerService computerService;

    public CtrlAddComputer() {
	super();
	page = new Page();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String displayCompanies(ModelMap model) {

	List<Company> companies = new ArrayList<Company>();
	companies = companyService.findAll();
	model.addAttribute("companies", companies);
	// redirection
	System.out.println("ADD_CTRL:GET");
	return "addComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(
	    ModelMap model,
	    @RequestParam(value = COMPUT_NAME, required = true) String name,
	    @RequestParam(value = INTRODUCED, required = false) String introduced,
	    @RequestParam(value = DISCONTINUED, required = false) String discontinued,
	    @RequestParam(value = COMPANY_ID, required = false) String companyId) {

	System.out.println("ADD_CTRL:POST");

	ComputerDTO computerDTO = ValidatorDTO
		.checkedComputerDTO(name, introduced,
			discontinued, companyId, "");

	computerService.create(MapperDTO.dtoToComputer(computerDTO));
	System.out.println(computerDTO.toString());
	page.init();
	page.setComputersDTO(MapperDTO.computersToDTO(computerService.findAll()));
	model.addAttribute("page", page);
	model.addAttribute("computersFound", computerService.count(""));

	return "dashboard";

    }

}
