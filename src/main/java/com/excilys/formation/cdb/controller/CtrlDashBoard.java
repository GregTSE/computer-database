package com.excilys.formation.cdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    
    private static final String OFFSET = "offset";
    private static final String INDEX = "index";
    private static final String SEARCH = "search";
    @Autowired
    private IComputerService computerService;
    private Page page;

    public CtrlDashBoard() {
	super();
	page = new Page();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model,  
	    	@RequestParam(value=SEARCH, required=false) String search, 
	        @RequestParam(value=INDEX, required=false) String index, 
	        @RequestParam(value=OFFSET, required=false) String offset){

	int  checkedIndex = 0;
	int checkedOffset = 10;

	if (Util.checkInt(index)) {
	    checkedIndex = Integer.parseInt(index);
	}

	if (Util.checkInt(offset)) {
	    checkedOffset = Integer.parseInt(offset);
	}
	
	if(search == null) {
	    search = "";
	}

	page.setPage(checkedIndex, checkedOffset, search);
	page.setComputersDTO(MapperDTO.computersToDTO(computerService.search(search, checkedIndex*checkedOffset, checkedOffset)));
	
	model.addAttribute("page", page);
	model.addAttribute("computersFound", computerService.count(search));
	return "dashboard";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(ModelMap model,  @RequestParam(value="selection", required=false) String selection ) {
	
  	String[] checkedComputersId = null;
  	if (selection != null) {
  	    if (selection.length() > 0) {
  		checkedComputersId =  selection.split(",");
  	    }
  	
  	    for (String checkedId :  checkedComputersId) {
  		computerService.delete(Integer.parseInt(checkedId));
  	    }
  	}
  	
  	page.init();
  	page.setComputersDTO(MapperDTO.computersToDTO(computerService.findAll()));
  	model.addAttribute("page", page);
  	model.addAttribute("computersFound", computerService.count(""));
  	return "dashboard";
      }

}
