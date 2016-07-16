package edu.umkc.permitme.domain.permit;

import java.util.ArrayList;

import edu.umkc.permitme.domain.Address;
import edu.umkc.permitme.domain.Project;

public class PlumbingPermitInformation extends PermitInformation {
	
	public PlumbingPermitInformation(Project project) {
		this.setPermitName("Plumbing ");
		this.setDepartmentName("City Planning and Development Department - Development Services");
		Address deptAddress = new Address();
		deptAddress.setStreetLine1("5th Floor, City Hall");
		deptAddress.setStreetLine2("414 East 12th Street");
		deptAddress.setCity("Kansas City");
		deptAddress.setState("MO");
		deptAddress.setZipCode("64106");
		this.setDepartmentAddress(deptAddress);
		// TODO - fill this in once the permit is available
		this.setPdfUri("#");
		this.setInstructions(new ArrayList<String>());
		this.getInstructions().add("Sign and date the form.");
		this.setPurpose("Verifies that the sewer service line running from the structure "
				+ "to the right-of-way or sewer easement meets building codes and city standards.");
	}

}
