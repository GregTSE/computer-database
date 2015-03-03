package com.excilys.formation.cdb.model;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.service.ComputerService;

public class Page {

    private int num;
    private int numGroup;
    private int begin;
    private int end;
    private int offset;
    private List<ComputerDTO> computersDTO;
    private int countComputers;
    private int computersByPage;
    private String word;

    private static final int NB_OF_DISPLAYED_PAGES = 5;

    public Page() {
	super();
    }
    
    public Page(int num, int offset, String word) {
	ComputerService cs = new ComputerService();
	this.num = num;
	this.numGroup = (num - 1) / NB_OF_DISPLAYED_PAGES;
	this.begin = (numGroup * NB_OF_DISPLAYED_PAGES + 1);
	this.end = (begin + NB_OF_DISPLAYED_PAGES - 1);
	this.offset = offset;
	this.computersDTO = new ArrayList<ComputerDTO>();
	List<Computer> computers;
	if (word.equals("")) {
	    computers = cs.findAll((num - 1) * offset, offset);
	} else {
	    computers = cs.search(word , (num - 1) * offset, offset);
	}
	this.word = word;
	for (Computer c : computers) {
	    computersDTO.add(MapperDTO.ComputerToDTO(c));
	}
	this.computersByPage = computersDTO.size();
	countComputers = cs.count();

    }

    public Page(int num, int offset) {
	this(num, offset, "");
    }

    public int getNum() {
	return num;
    }

    public void setNum(int num) {
	this.num = num;
    }

    public int getOffset() {
	return offset;
    }

    public void setOffset(int offset) {
	this.offset = offset;
    }

    public List<ComputerDTO> getComputersDTO() {
	return computersDTO;
    }

    public void setComputers(List<ComputerDTO> computersDTO) {
	this.computersDTO = computersDTO;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((computersDTO == null) ? 0 : computersDTO.hashCode());
	result = prime * result + num;
	result = prime * result + offset;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Page other = (Page) obj;
	if (computersDTO == null) {
	    if (other.computersDTO != null)
		return false;
	} else if (!computersDTO.equals(other.computersDTO))
	    return false;
	if (num != other.num)
	    return false;
	if (offset != other.offset)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Page [num=" + num + ", offset=" + offset + ", computers="
		+ computersDTO + "]";
    }

    public int getCountComputers() {
	return countComputers;
    }

    public void setCountComputers(int countComputers) {
	this.countComputers = countComputers;
    }

    public int getBegin() {
	return begin;
    }

    public void setBegin(int begin) {
	this.begin = begin;
    }

    public int getEnd() {
	return end;
    }

    public void setEnd(int end) {
	this.end = end;
    }

    public void setComputersDTO(List<ComputerDTO> computersDTO) {
	this.computersDTO = computersDTO;
    }

    public int getComputersByPage() {
	return computersByPage;
    }

    public void setComputersByPage(int computerByPage) {
	this.computersByPage = computerByPage;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
