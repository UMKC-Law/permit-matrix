package edu.umkc.permitme.domain.permit;

import java.util.ArrayList;

import edu.umkc.permitme.domain.Address;
import edu.umkc.permitme.domain.Project;

public class ExcavationPermitInformation extends PermitInformation {

	public ExcavationPermitInformation(Project project) {
		this.setPermitName("Excavation");
		this.setDepartmentName("Public Works Department - Street and Traffic Division");
		Address deptAddress = new Address();
		deptAddress.setStreetLine1("5th Floor, City Hall");
		deptAddress.setStreetLine2("414 East 12th Street");
		deptAddress.setCity("Kansas City");
		deptAddress.setState("MO");
		deptAddress.setZipCode("64106-2705");
		this.setDepartmentAddress(deptAddress);
		this.setPdfUri("api/permits/excavation/" + project.getId());
		this.setInstructions(new ArrayList<String>());
		this.getInstructions().add("Obtain the Sanitary Sewer Connection Authorization permit.");
		this.getInstructions().add("Obtain the Sewer Connection Card from the Water Department (issued in conjunction with applying for the Sewer Connection Inspection Permit).");
		this.getInstructions().add("Sign and date the form.");
		this.getInstructions().add("Attach the excavation plans or sketch.");
		this.setPurpose("Protects the safety of the general public, minimizes the impact to public assets in the Right-of-Way, and"
				+ " ensures that the public right-of-way is returned to its original integrity");
	}
	
}
