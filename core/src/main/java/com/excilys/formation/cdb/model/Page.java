package com.excilys.formation.cdb.model;


//TODO : Ne pas afficher les pages sans contenu
public class Page {
    
    public enum Sort {
	ASC,
	DESC
    }

    private int index;
    private int offset;
    private String search;
    private String sort;
    

    private static final int NB_OF_DISPLAYED_PAGES = 5;
    
    public Page() {
	super();
	init();
    }

    public Page(int index, int offset, String search, String sort) {
	super();
	this.index = index;
	this.offset = offset;
	this.search = search;
	this.sort = sort;
    }

    public void init() {
	this.index = 0;
	this.offset = 10;
	this.search = "";
	this.sort = "ASC";
    }

    public void setPage(int index, int offset, String search) {
	this.index = index;
	this.offset = offset;
	this.search = search;
    }

    public int getBeginPage() {
	return ((index - 1) / NB_OF_DISPLAYED_PAGES * NB_OF_DISPLAYED_PAGES + 1);
    }

    public int getEndPage() {
	return ((index - 1) / NB_OF_DISPLAYED_PAGES * NB_OF_DISPLAYED_PAGES + 1)
		+ (NB_OF_DISPLAYED_PAGES - 1);
    }

    public int getIndex() {
	return index;
    }

    public void setIndex(int index) {
	this.index = index;
    }

    public int getOffset() {
	return offset;
    }

    public void setOffset(int offset) {
	this.offset = offset;
    }

    public String getSearch() {
	return search;
    }

    public void setSearch(String search) {
	this.search = search;
    }

    public String getToogleSort() {
	if (sort.equals(Sort.ASC.name())) {
	    this.sort = "DESC";
	    return this.sort;
	} else {
	    this.sort = "ASC";
	    return this.sort;
	}
    }
    
    public String getSort() {
	return this.sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
