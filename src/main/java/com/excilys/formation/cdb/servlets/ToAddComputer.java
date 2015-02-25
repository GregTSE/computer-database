package com.excilys.formation.cdb.servlets;

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

/**
 * Servlet implementation class ToAddComputer
 */
@WebServlet("/ToAddComputer")
public class ToAddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToAddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

    	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    CompanyService companyService = new CompanyService();
	    List<Company> companies = new ArrayList<Company>();
	    companies = companyService.findAll();
	    
	    request.setAttribute("companies", companies);
	    //redirection
	    getServletContext().getRequestDispatcher("/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
