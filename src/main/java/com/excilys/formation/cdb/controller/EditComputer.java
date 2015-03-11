/**
 * 
 */
package com.excilys.formation.cdb.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * @author GregTSE
 *
 */
@WebServlet("/EditComputer")
@Configurable
public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
	super();
    }

    
    public void init(ServletConfig config) throws ServletException  {
	super.init(config);
	SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {

//	Page p = new Page(1, 10, "");
//
//	request.setAttribute("page", p);
	getServletContext().getRequestDispatcher("/views/editComputer.jsp")
		.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	String name = request.getParameter("computerName");
	String introduced = request.getParameter("introduced");
	String discontinued = request.getParameter("discontinued");
	String companyId = request.getParameter("companyId");
	//Page p = new Page(1, 10, "");

	//request.setAttribute("page", p);
	getServletContext().getRequestDispatcher("/views/editComputer.jsp")
		.forward(request, response);
    }

}
