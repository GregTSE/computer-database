package com.excilys.formation.cdb.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Computer {

    private Long id;
    private String name;
    private LocalDate dateIntroduced;
    private LocalDate dateDiscontinued;
    private Company company;

    public Computer() {
	super();
    }

    public Computer(Long id, String name, LocalDate dateIntroduced, LocalDate dateDiscontinued, Company company) {
	super();
	this.id = id;
	this.name = name;
	this.dateIntroduced = dateIntroduced;
	this.dateDiscontinued = dateDiscontinued;
	this.company = company;
    }

    public Computer(String name) {
	this(null, name, null, null, null);
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

    public LocalDate getDateIntroduced() {
	return dateIntroduced;
    }

    public void setDateIntroduced(LocalDate dateIntroduced) {
	this.dateIntroduced = dateIntroduced;
    }

    public LocalDate getDateDiscontinued() {
	return dateDiscontinued;
    }

    public void setDateDiscontinued(LocalDate dateDiscontinued) {
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
	// utiliser Builder
	String result = name;
	result += (dateIntroduced != null) ? ", Introduced : " + dateIntroduced
		: "";
	result += (dateDiscontinued != null) ? ", Discontinued : "
		+ dateDiscontinued : "";
	result += (company != null) ? "\t(Company : " + company.getName() + ")"
		: "";
	return result;
    }

    /**
     * @return a String with 'id' and 'name' of company
     */
    public String toBasicString() {
	return id + " : " + name;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((company == null) ? 0 : company.hashCode());
	result = prime * result	+ ((dateDiscontinued == null) ? 0 : dateDiscontinued.hashCode());
	result = prime * result	+ ((dateIntroduced == null) ? 0 : dateIntroduced.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	Computer other = (Computer) obj;
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
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }

}
