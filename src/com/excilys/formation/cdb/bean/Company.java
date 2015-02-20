package com.excilys.formation.cdb.bean;

public class Company {

	private int id;
	private String name;

	public Company() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Entreprise : " + name + " (id=" + id + ")\n";
	}

}
