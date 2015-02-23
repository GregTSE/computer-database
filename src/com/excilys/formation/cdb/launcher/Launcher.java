package com.excilys.formation.cdb.launcher;

import com.excilys.formation.cdb.controller.Controller;
import com.excilys.formation.cdb.view.Console;

/**
 * The main class
 * @author Gregori Tirsatine
 *
 */
public class Launcher {

    public static void main(String[] args) {
	Controller ctrl = new Controller();
	Console console = new Console(ctrl);
	console.start();
    }

}
