package com.excilys.formation.cdb.dto;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ComputerDTOTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Test
    public void test() {
	ComputerDTO c = new ComputerDTO("0123456789012345678901234567890123456789", "", "", "");
    }

}
