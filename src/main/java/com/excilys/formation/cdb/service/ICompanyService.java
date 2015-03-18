package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyService {

    /**
     * Get all the companies
     * @return List of the companies
     */
    public abstract List<Company> findAll();

    /**
     * Delete the company which id is 'id' and all the computers producted by this company
     * @param id
     */
    public abstract void delete(Long id);
    
    public abstract Company find(Long id);

}