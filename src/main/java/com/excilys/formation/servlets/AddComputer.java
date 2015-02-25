package com.excilys.formation.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.service.ComputerService;
import com.excilys.formation.cdb.utils.InputValidator;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/views/addComputer.jsp";
	private static final String COMPUT_NAME = "computerName";
	private static final String INTRODUCED = "introduced";
	private static final String DISCONTINUED = "discontinued";
	private static final String COMPANY_ID = "CompanyId";

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    /* Récupération des champs du formulaire. */

	        String name = request.getParameter(COMPUT_NAME);

	        String introduced = request.getParameter(INTRODUCED);

	        String discontinued = request.getParameter(DISCONTINUED);

	        String companyId = request.getParameter(COMPANY_ID);
	      
	        if (!InputValidator.checkDateFormat(discontinued)) {
	    		discontinued = null;
	    	}
	        if (!InputValidator.checkDateFormat(introduced)) {
	    		introduced = null;
	    	}
	        request.setAttribute("computerName", name);
	        request.setAttribute("introduced", introduced);
	        request.setAttribute("discontinued", discontinued);
	        request.setAttribute("companyId", companyId);

	        //verifier les champs
	        
	        //Ajouter a la BDD
	        ComputerService computerService = new ComputerService();
	        computerService.create(name, introduced, discontinued, "1");
	        //redirection
	        getServletContext().getRequestDispatcher("/views/debug.jsp").forward(request, response);

	}

}
