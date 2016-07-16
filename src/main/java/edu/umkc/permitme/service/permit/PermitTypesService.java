package edu.umkc.permitme.service.permit;

import java.util.ArrayList;
import java.util.List;

import edu.umkc.permitme.domain.Project;
import edu.umkc.permitme.domain.permit.ExcavationPermitInformation;
import edu.umkc.permitme.domain.permit.PermitInformation;
import edu.umkc.permitme.domain.permit.PlumbingPermitInformation;
import edu.umkc.permitme.domain.permit.SewerConnectAuthPermitInformation;

public class PermitTypesService {
	
	public List<PermitInformation> determineApplicablePermitsForProject(Project project) {
		
		List<PermitInformation> permits = new ArrayList<PermitInformation>();
		permits.add(new SewerConnectAuthPermitInformation(project));
		permits.add(new PlumbingPermitInformation(project));
		permits.add(new ExcavationPermitInformation(project));			
		
		return permits;
	}

}
