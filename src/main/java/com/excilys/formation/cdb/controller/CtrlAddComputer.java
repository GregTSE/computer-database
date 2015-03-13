package com.excilys.formation.cdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.ICompanyService;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.utils.Util;

@Controller
@RequestMapping("/addComputer")
public class CtrlAddComputer {
    
    private static final String COMPUT_NAME = "computerName";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "companyId";
    private static final String COMPANY_NAME = "companyName";

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IComputerService computerService;
    
    public CtrlAddComputer() {
	super();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    protected String doGet(ModelMap model) {

	List<Company> companies = new ArrayList<Company>();
	companies = companyService.findAll();
	model.addAttribute("companies", companies);
	// redirection
	return "addComputer";
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(ModelMap model,
	    @RequestParam(value="COMPUT_NAME", required=true) String name, 
	    @RequestParam(value="INTRODUCED", required=false) String introduced, 
	    @RequestParam(value="DISCONTINUED", required=false) String discontinued,
	    @RequestParam(value="COMPANY_ID", required=false) String companyId) {

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
	// check fields !!!

	// Add intto the db

	computerService.create(name, introduced, discontinued, company);

	// Page page = new Page();
	// request.setAttribute("page", page);
	return "dashboard";

    }

}
