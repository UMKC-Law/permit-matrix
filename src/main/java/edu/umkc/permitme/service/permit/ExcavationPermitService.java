package edu.umkc.permitme.service.permit;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.umkc.permitme.domain.Contractor;
import edu.umkc.permitme.domain.Project;
import edu.umkc.permitme.domain.permit.ExcavationPermit;
import edu.umkc.permitme.repository.ProjectRepository;

@Service
public class ExcavationPermitService {

	@Inject
	ProjectRepository projectRepo;
	
	public ExcavationPermit getExcavationPermit(Long projectId) {
		Project project = projectRepo.findOne(projectId);
		Contractor contractor = project.getContractor();
	    ExcavationPermit excavation = new ExcavationPermit();
	    excavation.setApplicantName(contractor.getBusinessName());
	    excavation.setApplicantEmail(contractor.getEmail());
	    // TODO - refactor the database to use a more appropriate field
	    excavation.setBore(project.getExcavationPermitNumber());
	    excavation.setBusinessAddress(contractor.getStreetAddress());	    
	    excavation.setCityStateZip(contractor.getCity()+
	    		", " + contractor.getState() + 
	    		" " + contractor.getZipCode());
	    excavation.setContactPhone(contractor.getAgentPhoneNumber());
	    excavation.setFax("N/A");
	    excavation.setResponsibleFirm(contractor.getBusinessName());
	    excavation.setJobAddress(project.getStreetAddress());
	    excavation.setPhone(contractor.getPhoneNumber());
	    excavation.setWorkDescription(project.getProjectDescription());
	    excavation.setSuperintendent(contractor.getAgentFirstName() + " " +
	    		contractor.getAgentMiddleInitial() + " " + contractor.getAgentLastName());
	    excavation.setExcavationDescription(project.getExcavationDescription());
		return excavation;
	}

}
