package com.excilys.formation.cdb.dto;

import com.excilys.formation.cdb.model.Company;

public class ComputerDTO {
    
    public ComputerDTO() {
	super();
    }
    
    public ComputerDTO(long id, String name, String dateIntroduced,
	    String dateDiscontinued, Company company) {
	super();
	this.id = id;
	this.name = name;
	this.dateIntroduced = dateIntroduced;
	this.dateDiscontinued = dateDiscontinued;
	this.company = company;
    }
    private long id;
	private String name;
	private String dateIntroduced;
	private String dateDiscontinued;
	private Company company;
	public long getId() {
	    return id;
	}
	public void setId(long id) {
	    this.id = id;
	}
	public String getName() {
	    return name;
	}
	public void setName(String name) {
	    this.name = name;
	}
	public String getDateIntroduced() {
	    return dateIntroduced;
	}
	public void setDateIntroduced(String dateIntroduced) {
	    this.dateIntroduced = dateIntroduced;
	}
	public String getDateDiscontinued() {
	    return dateDiscontinued;
	}
	public void setDateDiscontinued(String dateDiscontinued) {
	    this.dateDiscontinued = dateDiscontinued;
	}
	public Company getCompany() {
	    return company;
	}
	public void setCompany(Company company) {
	    this.company = company;
	}
	@Override
	public String toString() {
	    return "ComputerDTO [id=" + id + ", name=" + name
		    + ", dateIntroduced=" + dateIntroduced
		    + ", dateDiscontinued=" + dateDiscontinued + ", company="
		    + company + "]";
	}
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result
		    + ((company == null) ? 0 : company.hashCode());
	    result = prime
		    * result
		    + ((dateDiscontinued == null) ? 0 : dateDiscontinued
			    .hashCode());
	    result = prime
		    * result
		    + ((dateIntroduced == null) ? 0 : dateIntroduced.hashCode());
	    result = prime * result + (int) (id ^ (id >>> 32));
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	    ComputerDTO other = (ComputerDTO) obj;
	    if (company == null) {
		if (other.company != null)
		    return false;
	    } else if (!company.equals(other.company))
		return false;
	    if (dateDiscontinued == null) {
		if (other.dateDiscontinued != null)
		    return false;
	    } else if (!dateDiscontinued.equals(other.dateDiscontinued))
		return false;
	    if (dateIntroduced == null) {
		if (other.dateIntroduced != null)
		    return false;
	    } else if (!dateIntroduced.equals(other.dateIntroduced))
		return false;
	    if (id != other.id)
		return false;
	    if (name == null) {
		if (other.name != null)
		    return false;
	    } else if (!name.equals(other.name))
		return false;
	    return true;
	}

}
