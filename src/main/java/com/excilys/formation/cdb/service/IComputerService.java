package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

public interface IComputerService {

    public abstract List<Computer> findAll();

    public abstract List<Computer> findAll(int num, int offset);

    public abstract Computer find(int id);

    public abstract void create(String name, String introduced,
	    String discontinued, Company company);

    public abstract void delete(int id);

    public abstract void update(Computer computer);

    public abstract List<Computer> search(String str, int num, int offset);

    public abstract int count(String word);

}