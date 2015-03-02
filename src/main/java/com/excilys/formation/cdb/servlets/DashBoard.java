package com.excilys.formation.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.utils.Util;

/**
 * Servlet implementation class DashBoard
 */
@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoard() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	int offset = 10;
	int index = 1;

	String strIndex = request.getParameter("index");
	String strOffset = request.getParameter("offset");

	if (Util.checkId(strIndex)) {
	    index = Integer.parseInt(strIndex);
	}

	if (Util.checkId(strOffset)) {
	    offset = Integer.parseInt(strOffset);
	}

	Page p = new Page(index, offset);

	request.setAttribute("page", p);
	getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

	// } else {
	//
	// ComputerService computerService = new ComputerService();
	// List<Computer> computers = new ArrayList<Computer>();
	// computers = computerService.findAll();
	//
	// request.setAttribute("computers", computers);
	// getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request,
	// response);
	// }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub

    }

}
