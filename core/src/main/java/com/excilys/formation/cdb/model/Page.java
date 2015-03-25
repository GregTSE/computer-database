package com.excilys.formation.cdb.model;

//TODO : Ne pas afficher les pages sans contenu
public class Page {

    private int index;
    private int offset;
    private String search;

    private static final int NB_OF_DISPLAYED_PAGES = 5;

    public Page() {
	this.index = 0;
	this.offset = 10;
	this.search = "";
    }

    public Page(int index, int offset, String search) {
	super();
	this.index = index;
	this.offset = offset;
	this.search = search;
    }

    public void init() {
	this.index = 0;
	this.offset = 10;
	this.search = "";
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


}
