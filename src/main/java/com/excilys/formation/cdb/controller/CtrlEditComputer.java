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
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.utils.Util;

@Controller
@RequestMapping("/editComputer")
public class CtrlEditComputer {

    private static final String COMPUT_NAME = "computerName";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "companyId";
    private static final String PARAM_ID = "id";

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IComputerService computerService;

    public CtrlEditComputer() {
	super();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String doGet(ModelMap model,
	    @RequestParam(value = PARAM_ID, required = true) String pId) {

	
	List<Company> companies = new ArrayList<Company>();

	companies = companyService.findAll();
	Computer computer = null;
	
	if (Util.checkDigit(pId)) {
	    computer = computerService.find(Long.parseLong(pId));
	}

	model.addAttribute("companies", companies);
	model.addAttribute("computer", computer);
	// redirection
	return "editComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(
	    ModelMap model,
	    @RequestParam(value = COMPUT_NAME, required = true) String name,
	    @RequestParam(value = INTRODUCED, required = false) String introduced,
	    @RequestParam(value = DISCONTINUED, required = false) String discontinued,
	    @RequestParam(value = COMPANY_ID, required = false) String companyId) {

	
	if (!Util.checkDateFormat(discontinued)) {
	    discontinued = null;
	}
	if (!Util.checkDateFormat(introduced)) {
	    introduced = null;
	}

	model.addAttribute(COMPUT_NAME, name);
	model.addAttribute(INTRODUCED, introduced);
	model.addAttribute(DISCONTINUED, discontinued);

	Company company = new Company(Long.parseLong(companyId), name);
	model.addAttribute("company", company);

	Long id = null;
	if (Util.checkDigit(companyId)) {
	    id = Long.parseLong(companyId);
	}
	 computerService.update(MapperDTO.dtoToComputer(new ComputerDTO(0,name,
	 introduced, discontinued,id.longValue(), name)));

	return "dashboard";

    }

}
