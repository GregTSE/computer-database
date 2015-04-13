package com.excilys.formation.cdb.service;

import java.util.List;

import com.excilys.formation.cdb.dto.ComputerDTO;

public interface IComputerService {

    /**
     * Get all the computers in the database
     * @return List of the computers
     */
    public abstract List<ComputerDTO> findAll();

    /**
     * Get the computer by id
     * @param id
     * @return computer
     */
    public abstract ComputerDTO find(long id);

    /**
     * insert a new computer in the database
     * @param computer
     */
    public abstract void insert(ComputerDTO computer);

    /**
     * delete the computer
     * @param id
     */
    public abstract void delete(long id);

    /**
     * update the fields of the computer
     * @param computer
     */
    public abstract void update(ComputerDTO computer);

    /**
     * Get the computers beginning by 'str' between 'num' and 'num+offset'
     * @param str 
     * @param num
     * @param offset
     * @param sort 
     * @return List of computers
     */
    public abstract List<ComputerDTO> search(String str, int num, int offset, String sort);

    /**
     * Count the computers beginning by 'prefix'
     * @param word
     * @return the number of computers beginning by 'prefix'
     */
    public abstract int count(String prefix);

}