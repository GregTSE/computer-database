package com.excilys.formation.cdb.dto;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.cdb.model.Computer;

public class MapperDTOTest {

    private static Computer computer;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	computer = new Computer();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void computerToDTOTest(){
	computer.setId(10);
	computer.setName("Comp");
	ComputerDTO computerDTO = MapperDTO.computerToDTO(computer);
	ComputerDTO compDTO = new ComputerDTO(10, "Comp","", "", 0, "");
	assertEquals(compDTO, computerDTO);
    }
    


}
