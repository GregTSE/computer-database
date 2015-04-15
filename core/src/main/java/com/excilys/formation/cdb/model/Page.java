package com.excilys.formation.cdb.model;

public class Page {

    public enum Sort {
	ASC, DESC
    }

    private int index;
    private int offset;
    private String search;
    private String sort;

    private static final int NB_OF_DISPLAYED_PAGES = 5;

    public Page() {
	super();
	this.index = 0;
	this.offset = 10;
	this.search = "";
	this.sort = "ASC";
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
	return (index / NB_OF_DISPLAYED_PAGES * NB_OF_DISPLAYED_PAGES + 1);
    }

    public int getEndPage() {
	return (index / NB_OF_DISPLAYED_PAGES * NB_OF_DISPLAYED_PAGES + 1)
		+ (NB_OF_DISPLAYED_PAGES);
    }

    public int getEndPage(int computers) {
	int lastPage = computers / offset + 1;
	int endPage = (index / NB_OF_DISPLAYED_PAGES * NB_OF_DISPLAYED_PAGES + 1)
		+ (NB_OF_DISPLAYED_PAGES - 1);
	if (endPage > lastPage) {
	    return lastPage;
	} else {
	    return endPage;
	}
    }

    public int getIndex() {
	return index;
    }
    
    public int getNextIndex(int computers) {
	int lastPage = computers / offset + 1;
	if (index+1 >= lastPage ) {
	    return index;
	} else {
	    return index+1;
	}
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

    public String getSort() {
	return this.sort;
    }

    public void setSort(String sort) {
	this.sort = sort;
    }

    public String getToogle() {
	if (sort.equals(Sort.ASC.name())) {
	    return "DESC";
	} else {
	    return "ASC";
	}
    }

}
