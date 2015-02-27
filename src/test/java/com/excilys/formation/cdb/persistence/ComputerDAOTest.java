package com.excilys.formation.cdb.persistence;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.Connection;

public class ComputerDAOTest {
    
    private static Connection connection;
    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//	//open connection before test cases
//	connection =  ConnectionDAO.getInstance();
//    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	//close connection after test cases
	connection.close();
    }

    @Test
    public void testCreate() {
	//create("CompTest", "2000-01-01", "2000-01-01", 1);
    }
    
    @Test
    public void testFind() {
	
    }

    @Test
    public void testFindAll() {
	fail("Not yet implemented");
    }



    @Test
    public void testUpdate() {
	fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
	fail("Not yet implemented");
    }

}
