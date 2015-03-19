package com.excilys.formation.cdb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ComputerDTO {

    private long id;

    @Size(min=5, max=30)
    private String name;
 
    private String dateIntroduced;
    private String dateDiscontinued;
    private long companyId;
    private String companyName;

    public ComputerDTO() {
	super();
    }

    public ComputerDTO(long id, String name, String dateIntroduced, String dateDiscontinued, long companyId, String companyName) {
	super();
	this.id = id;
	this.name = name;
	this.dateIntroduced = dateIntroduced;
	this.dateDiscontinued = dateDiscontinued;
	this.companyId = companyId;
	this.companyName = companyName;
    }

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

    public long getCompanyId() {
	return companyId;
    }

    public String getCompanyName() {
	return companyName;
    }

    public void setCompanyId(long companyId) {
	this.companyId = companyId;
    }

    public void setCompanyName(String companyName) {
	this.companyName = companyName;
    }

    @Override
    public String toString() {
	return "ComputerDTO [id=" + id + ", name=" + name + ", dateIntroduced="
		+ dateIntroduced + ", dateDiscontinued=" + dateDiscontinued
		+ ", company=" + companyName + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (companyId ^ (companyId >>> 32));
	result = prime * result
		+ ((companyName == null) ? 0 : companyName.hashCode());
	result = prime
		* result
		+ ((dateDiscontinued == null) ? 0 : dateDiscontinued.hashCode());
	result = prime * result
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
	if (companyId != other.companyId)
	    return false;
	if (companyName == null) {
	    if (other.companyName != null)
		return false;
	} else if (!companyName.equals(other.companyName))
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
