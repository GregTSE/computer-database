package com.excilys.formation.cdb.dao;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class ConnectionDAO {

	private static Connection conn = null;

	private ConnectionDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";
			conn = (Connection) DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getInstance() {
		if (conn == null) {
			new ConnectionDAO();
		}
		return conn;
	}

}
