package com.excilys.formation.cdb.persistence.implementation;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestCompanyDAO {

    private static Connection connection;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

	// ConnectionDAO4Test.INSTANCE.init();
	// connection = ConnectionDAO4Test.INSTANCE.connection;
	// connection.createStatement().executeUpdate(CREATE);
	// connection.createStatement().executeUpdate(INSERT);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
	connection.close();
    }

    // @Test
    // public void testFindAll() {
    // assertEquals(CompanyDAO.INSTANCE.findAll(connection).size(), 3);
    // }

}
