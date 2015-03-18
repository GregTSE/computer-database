package com.excilys.formation.cdb.dto;

import com.excilys.formation.cdb.utils.Util;

public class ValidatorDTO {

    public ValidatorDTO() {
	super();
    }

    public static ComputerDTO checkedComputerDTO(String pComputerName, String pIntroduced, String pDiscontinued,
	    String pCompanyId, String pCompanyName) {
	String discontinued = pDiscontinued;
	String introduced = pIntroduced;
	long companyId = 0;
	String companyName = "";
	long computerId = 0;

	if (!Util.checkDateFormat(discontinued)) {
	    discontinued = null;
	}
	if (!Util.checkDateFormat(introduced)) {
	    introduced = null;
	}
	
	if (Util.checkDigit(pCompanyId)) {
	    companyId = Long.parseLong(pCompanyId);
	}

	return new ComputerDTO(computerId, pComputerName, introduced, discontinued, companyId, companyName);
    }

}
