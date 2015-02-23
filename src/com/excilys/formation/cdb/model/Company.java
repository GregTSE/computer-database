package com.excilys.formation.cdb.model;

public class Company {

	private Long id;
	private String name;

	public Company() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = new Long(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
