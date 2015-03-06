package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;

public interface ICompanyService {

    public abstract List<Company> findAll();

    public abstract void delete(Long id);

}