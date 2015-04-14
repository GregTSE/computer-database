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

import com.excilys.formation.cdb.dto.ComputerDTO;
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

	System.out.println("Et c'est parti pour le show !");
	if (bindingResult.hasErrors()) {
	    System.out.println("Ereuuur");
	    model.addAttribute("computerDTO", computerDTO);
	    List<Company> companies = new ArrayList<Company>();
	    companies = companyService.findAll();
	    model.addAttribute("companies", companies);
	    return "addComputer";
	} else {
	    System.out.println("padreuuur");
	    // Insert in the database
	    computerService.insert(computerDTO);
	    // Redirection
	    Page page = new Page();
	    List<ComputerDTO> computersDTO = computerService.findAll();
	    model.addAttribute("computersDTO",computersDTO);
	    model.addAttribute("page", page);
	    return "redirect:/dashboard";
	}
    }

}
