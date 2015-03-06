package com.excilys.formation.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;

import com.excilys.formation.cdb.model.Company;
import com.excilys.formation.cdb.model.Computer;

public class MapperDAO {

    public MapperDAO() {
	super();
    }

    // public static ResultSet computerToRow(Computer computer){
    // }

    public static Computer rowToComputer(ResultSet results) {

	Computer computer = null;
	
	try {
	    Company company = null;
	    Long idCompany = results.getLong(5);
	    
	    if (idCompany > 0) {
		company = new Company(idCompany, results.getString(6));
	    }
	    
	    Timestamp introducedTS = results.getTimestamp(3);
	    LocalDate introduced = null, discontinued = null;
	    Timestamp discontinuedTS = results.getTimestamp(4);

	    if (introducedTS != null) {
		introduced = introducedTS.toLocalDateTime().toLocalDate();
	    }

	    if (discontinuedTS != null) {
		discontinued = discontinuedTS.toLocalDateTime().toLocalDate();
	    }

	    computer = new Computer(results.getLong(1), results.getString(2), introduced,
		    discontinued, company);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return computer;
    }
}
