package com.excilys.formation.cdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.service.CompanyService;
import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.utils.Util;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String COMPUT_NAME = "computerName";
    private static final String INTRODUCED = "introduced";
    private static final String DISCONTINUED = "discontinued";
    private static final String COMPANY_ID = "companyId";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	CompanyService companyService = new CompanyService();
	List<Company> companies = new ArrayList<Company>();
	companies = companyService.findAll();
	request.setAttribute("companies", companies);
	// redirection
	getServletContext().getRequestDispatcher("/views/addComputer.jsp")
		.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String name = request.getParameter(COMPUT_NAME);
	String introduced = request.getParameter(INTRODUCED);
	String discontinued = request.getParameter(DISCONTINUED);
	String companyId = request.getParameter(COMPANY_ID);

	if (!Util.checkDateFormat(discontinued)) {
	    discontinued = null;
	}
	if (!Util.checkDateFormat(introduced)) {
	    introduced = null;
	}

	request.setAttribute("computerName", name);
	request.setAttribute("introduced", introduced);
	request.setAttribute("discontinued", discontinued);

	Company company = new Company(Long.parseLong(companyId), name);
	request.setAttribute("company", company);
	// check fields !!!

	// Add intto the db
	ComputerService computerService = new ComputerService();
	computerService.create(name, introduced, discontinued, company);

	// redirection
	response.sendRedirect("./DashBoard");
    }

}
