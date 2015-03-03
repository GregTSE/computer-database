package com.excilys.formation.cdb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.ComputerService;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int offset = 10;
	int index = 1;

	String strIndex = request.getParameter("index");
	String strOffset = request.getParameter("offset");

	if (Util.checkInt(strIndex)) {
	    index = Integer.parseInt(strIndex);
	}

	if (Util.checkInt(strOffset)) {
	    offset = Integer.parseInt(strOffset);
	}
	
	String word = request.getParameter("search");
	if(word == null) {
	    word = "";
	}

	Page p = new Page(index, offset, word);
	
	request.setAttribute("page", p);
	getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String[] checkedComputersId = null;
	if (request.getParameter("selection") != null) {
	    if (request.getParameter("selection").length() > 0) {
		checkedComputersId =  request.getParameter("selection").split(",");
	    }
	}
	
	ComputerService cs = new ComputerService();
	for (String checkedId :  checkedComputersId) {
	 cs.delete(Integer.parseInt(checkedId));
	} 
	Page p = new Page(1, 10, "");
	
	request.setAttribute("page", p);
	getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

}
