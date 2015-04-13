package com.excilys.formation.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.utils.Util;

@Controller
@RequestMapping("/editComputer")
public class CtrlEditComputer {

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
	ComputerDTO computerDTO = null;

	if (Util.checkDigit(pId)) {
	    computerDTO = computerService.find(Long.parseLong(pId));
	}

	model.addAttribute("computer", computerDTO);
	model.addAttribute("companies", companies);
	// redirection
	return "editComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(ModelMap model, @Valid ComputerDTO computerDTO,
	    BindingResult bindingResult) {

	if (bindingResult.hasErrors()) {
	    model.addAttribute("computerDTO", computerDTO);
	    List<Company> companies = new ArrayList<Company>();
	    companies = companyService.findAll();
	    model.addAttribute("companies", companies);
	    return "editComputer";
	} else {
	    // Insert in the database
	    computerService.update(computerDTO);
	    // Redirection
	    Page page = new Page();
	    List<ComputerDTO> computersDTO = computerService.findAll();
	    model.addAttribute("computersDTO", computersDTO);
	    model.addAttribute("page", page);
	    model.addAttribute("computersFound", computerService.count(""));
	    return "forward:/dashboard";
	}
    }

}
