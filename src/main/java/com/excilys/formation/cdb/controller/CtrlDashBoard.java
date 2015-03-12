package com.excilys.formation.cdb.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.utils.Util;

@Controller
@RequestMapping("/dashboard")
public class CtrlDashBoard {
    
    @Autowired
    private IComputerService computerService;
    private Page page;

    public CtrlDashBoard() {
	super();
	page = new Page();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(HttpServletRequest request,  
	    	@RequestParam(required=false) String search, 
	        @RequestParam(value="0", required=false) String index, 
	        @RequestParam(value="10", required=false) String offset){

	

	int  checkedIndex = 0;
	int checkedOffset = 10;

	if (Util.checkInt(index)) {
	    checkedIndex = Integer.parseInt(index);
	}

	if (Util.checkInt(offset)) {
	    checkedOffset = Integer.parseInt(offset);
	}
	;
	if(search == null) {
	    search = "";
	}

	page.setPage(checkedIndex, checkedOffset, search);
	page.setComputersDTO(MapperDTO.computersToDTO(computerService.search(search, checkedIndex*checkedOffset, checkedOffset)));
	
	request.setAttribute("page", page);
	request.setAttribute("computersFound", computerService.count(search));
	return "dashboard";
	//getServletContext().getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }

}
