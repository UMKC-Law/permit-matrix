package edu.umkc.permitme.domain.permit;

import java.util.ArrayList;

import edu.umkc.permitme.domain.Address;
import edu.umkc.permitme.domain.Project;

public class SewerConnectAuthPermitInformation extends PermitInformation {
	
	public SewerConnectAuthPermitInformation(Project project) {
		this.setPermitName("Sanitary Sewer Connection Authorization ");
		this.setDepartmentName("City Planning and Development Department - Development Services");
		Address deptAddress = new Address();
		deptAddress.setStreetLine1("5th Floor, City Hall");
		deptAddress.setStreetLine2("414 East 12th Street");
		deptAddress.setCity("Kansas City");
		deptAddress.setState("MO");
		deptAddress.setZipCode("64106");
		this.setDepartmentAddress(deptAddress);
		// TODO - fill this in once the permit is available
		this.setPdfUri("#s");
		this.setInstructions(new ArrayList<String>());
		this.getInstructions().add("Sign and date the form.");
		this.setPurpose("Checks whether a) the sewer is available & has capacity for the connection"
				+ ", and b) there are sewer district or other assessments due.");
	}

}
