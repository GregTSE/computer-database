package com.excilys.formation.cdb.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.formation.cdb.dto.ComputerDTO;
import com.excilys.formation.cdb.dto.MapperDTO;
import com.excilys.formation.cdb.persistence.IComputerDAO;
import com.excilys.formation.cdb.service.IComputerService;

@Service
@Transactional
public class ComputerService implements IComputerService {
    
    @Autowired
    private IComputerDAO computerDAO;
    
    public ComputerService() {
	super();
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#findAll()
     */
    @Override
    public List<ComputerDTO> findAll() {
	return MapperDTO.computersToDTO(computerDAO.findAll());
	
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#find(int)
     */
    @Override
    public ComputerDTO find(long id) {
	return MapperDTO.computerToDTO(computerDAO.find(id));
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#create(java.lang.String, java.lang.String, java.lang.String, com.excilys.formation.cdb.model.Company)
     */
    @Override
    public void insert(ComputerDTO computerDTO) {
	computerDAO.create(MapperDTO.dtoToComputer(computerDTO));
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#delete(int)
     */
    @Override
    public void delete(long id) {
	computerDAO.delete(id);
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#update(com.excilys.formation.cdb.model.Computer)
     */
    @Override
    public void update(ComputerDTO computerDTO) {
	computerDAO.update(MapperDTO.dtoToComputer(computerDTO));
    }

    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#search(java.lang.String, int, int)
     */
    @Override
    public List<ComputerDTO> search(String str, int num, int offset, String sort) {
	return MapperDTO.computersToDTO(computerDAO.search(str, num, offset, sort));
    }
    
    /* (non-Javadoc)
     * @see com.excilys.formation.cdb.service.IComputerService#count(java.lang.String)
     */
    @Override
    public int count(String word) {
	return computerDAO.count(word);
    }
     
}
