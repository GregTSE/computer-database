package com.excilys.formation.cdb.model;

import java.util.List;

import com.excilys.formation.cdb.dto.ComputerDTO;


public class Page {
    
    private int num;
    private int offset;
    private String word;
    private List<ComputerDTO> computersDTO;

    
//    public int countComputers;
   
    private static final int NB_OF_DISPLAYED_PAGES = 5;

    public Page(){
	this.num = 0;
	this.offset = 10;
	this.word = "";
    }
    
    public void init() {
	this.num = 0;
	this.offset = 10;
	this.word = "";
    }
    
    public void setPage(int num, int offset, String word) {
	this.num = num;
	this.offset = offset;
	this.word = word;
    }
    
    public int getBeginPage(){
	return ((num - 1) / NB_OF_DISPLAYED_PAGES* NB_OF_DISPLAYED_PAGES + 1);
    }
    
    public int getEndPage(){
	return ((num - 1) / NB_OF_DISPLAYED_PAGES* NB_OF_DISPLAYED_PAGES + 1)+ (NB_OF_DISPLAYED_PAGES - 1);
    }
    
    public int getNbComputersPerPage(){
	return computersDTO.size();
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

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<ComputerDTO> getComputersDTO() {
        return computersDTO;
    }

    public void setComputersDTO(List<ComputerDTO> computersDTO) {
        this.computersDTO = computersDTO;
    }


}
