package com.excilys.formation.cdb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.service.implementation.ComputerService;
import com.excilys.formation.cdb.utils.Util;

/**
 * Servlet implementation class DashBoard
 */
@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IComputerService computerService;
    private Page page;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashBoard() {
	super();
	page = new Page();
	computerService = new ComputerService();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int offset = 10;
	int index = 0;

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

	page.setPage(index, offset, word);
	page.setComputersDTO(MapperDTO.computersToDTO(computerService.search(word, index*offset, offset)));
	
	request.setAttribute("page", page);
	request.setAttribute("computersFound", computerService.count(word));
	
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
	
	for (String checkedId :  checkedComputersId) {
	    computerService.delete(Integer.parseInt(checkedId));
	}
	
	page.init();
	
	request.setAttribute("page", page);
	
	getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

}
