package com.excilys.formation.cdb.launcher;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.excilys.formation.cdb.model.Computer;

public class LauncherTest {

    @Test
    public void test() {
	Computer c = Mockito.mock(Computer.class);
	// define return value for method getUniqueId()
	Mockito.when(c.getName()).thenReturn("Acer");
	System.out.println(c.getName());
	assertEquals(c.getName(), "Acer");

    }

}
