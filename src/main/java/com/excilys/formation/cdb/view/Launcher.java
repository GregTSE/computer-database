package com.excilys.formation.cdb.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Launcher to execute the application with a console interface
 * 
 * @author Gregori Tirsatine
 *
 */
public class Launcher {

    public static void main(String[] args) {
	ApplicationContext context = new ClassPathXmlApplicationContext("/application-context.xml");
	Console console = context.getBean(Console.class);
	
	console.start();
	((AbstractApplicationContext) context).close();

    }

}
