package com.excilys.formation.cdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.model.Page;
import com.excilys.formation.cdb.service.IComputerService;
import com.excilys.formation.cdb.utils.Util;

@Controller
@RequestMapping("/dashboard")
public class CtrlDashBoard {

    @Autowired
    private IComputerService computerService;

    private static final String OFFSET = "offset";
    private static final String INDEX = "index";
    private static final String SEARCH = "search";

    public CtrlDashBoard() {
	super();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayComputers(ModelMap model,
	    @RequestParam(value = SEARCH, required = false) String search,
	    @RequestParam(value = INDEX, required = false) String index,
	    @RequestParam(value = OFFSET, required = false) String offset) {

	int checkedIndex = 0;
	int checkedOffset = 10;

	if (Util.checkDigit(index)) {
	    checkedIndex = Integer.parseInt(index);
	}

	if (Util.checkDigit(offset)) {
	    checkedOffset = Integer.parseInt(offset);
	}

	if (search == null) {
	    search = "";
	}

	Page page = new Page(checkedIndex, checkedOffset, search);
	List<ComputerDTO> computersDTO = MapperDTO
		.computersToDTO(computerService.search(search, checkedIndex
			* checkedOffset, checkedOffset));
	model.addAttribute("computersDTO", computersDTO);
	model.addAttribute("page", page);
	model.addAttribute("computersFound", computerService.count(search));

	return "dashboard";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String deleteComputers(
	    ModelMap model,
	    @RequestParam(value = "selection", required = false) String selection) {

	String[] checkedComputersId = null;
	if (selection != null) {
	    if (selection.length() > 0) {
		checkedComputersId = selection.split(",");
	    }

	    for (String checkedId : checkedComputersId) {
		computerService.delete(Integer.parseInt(checkedId));
	    }
	}

	Page page = new Page();

	List<ComputerDTO> computersDTO = MapperDTO.computersToDTO(computerService.findAll());
	model.addAttribute("computersDTO", computersDTO);
	model.addAttribute("page", page);
	model.addAttribute("computersFound", computerService.count(""));

	return "dashboard";
    }
    
}
