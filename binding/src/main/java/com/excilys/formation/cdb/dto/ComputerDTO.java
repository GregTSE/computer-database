package com.excilys.formation.cdb.dto;

import java.util.Locale;
import com.excilys.formation.cdb.utils.Util;

import javax.validation.constraints.Size;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.formation.cdb.utils.DateAnnotation;

public class ComputerDTO {

    private long id;

    @Size(min = 1, max = 30)
    private String name;
    @DateAnnotation
    private String dateIntroduced;
    @DateAnnotation
    private String dateDiscontinued;
    private long companyId;
    private String companyName;

    public ComputerDTO() {
	super();
    }

    public ComputerDTO(long id, String name, String dateIntroduced,
	    String dateDiscontinued, long companyId, String companyName) {
	super();
	this.id = id;
	this.name = name;
	this.dateIntroduced = dateIntroduced;
	this.dateDiscontinued = dateDiscontinued;
	this.companyId = companyId;
	this.companyName = companyName;
    }

    public ComputerDTO(String name, String dateIntroduced,
	    String dateDiscontinued, String companyName) {
	this(0, name, dateIntroduced, dateDiscontinued, 0, companyName);
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
	Locale locale = LocaleContextHolder.getLocale();
	return locale.getLanguage().equals("fr") ? Util
		.englishToFrenchDate(this.dateIntroduced) : this.dateIntroduced;
    }

    public void setDateIntroduced(String dateIntroduced) {
	Locale locale = LocaleContextHolder.getLocale();
	this.dateIntroduced = locale.getLanguage().equals("fr") ? Util
		.frenchToEnglishDate(dateIntroduced) : dateIntroduced;
    }

    public String getDateDiscontinued() {
	Locale locale = LocaleContextHolder.getLocale();
	return locale.getLanguage().equals("fr") ? Util
		.englishToFrenchDate(this.dateDiscontinued)
		: this.dateDiscontinued;
    }

    public String getEnglishDateDiscontinued() {
	return this.dateDiscontinued;
    }
    
    public String getEnglishDateIntroduced() {
	return this.dateIntroduced;
    }

    public void setDateDiscontinued(String dateDiscontinued) {
	Locale locale = LocaleContextHolder.getLocale();
	this.dateDiscontinued = locale.getLanguage().equals("fr") ? Util
		.frenchToEnglishDate(dateDiscontinued) : dateDiscontinued;
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
	String introduced = (dateIntroduced == null) ? "" : ", introduced:"
		+ dateIntroduced;
	String discontinued = (dateDiscontinued == null) ? ""
		: ", discontinued:" + dateDiscontinued;
	String company = (companyName == null || companyName.equals("")) ? ""
		: " [Company : " + companyName + "]";
	return id + " : " + name + introduced + discontinued + company;
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
