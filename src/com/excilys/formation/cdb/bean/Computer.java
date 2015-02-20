package com.excilys.formation.cdb.bean;

import java.sql.Date;


public class Computer {

	private int id;
	private String name;
	private Date dateIntroduced;
	private Date dateDiscontinued;
	private String company;
	
	public Computer(){
		super();
	}
	
	public Computer(int id, String name, Date dateIntroduced,
			Date dateDiscontinued, String company) {
		super();
		this.id = id;
		this.name = name;
		this.dateIntroduced = dateIntroduced;
		this.dateDiscontinued = dateDiscontinued;
		this.company = company;
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

	public Date getDateIntroduced() {
		return dateIntroduced;
	}

	public void setDateIntroduced(Date dateIntroduced) {
		this.dateIntroduced = dateIntroduced;
	}

	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(Date dateDiscontinued) {
			this.dateDiscontinued = dateDiscontinued;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", dateIntroduced="
				+ dateIntroduced + ", dateDiscontinued=" + dateDiscontinued
				+ ", company=" + company + "]";
	}
	
	
	
	
}
