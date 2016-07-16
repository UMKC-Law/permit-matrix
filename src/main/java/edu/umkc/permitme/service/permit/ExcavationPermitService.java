package edu.umkc.permitme.service.permit;

import org.springframework.stereotype.Service;

import edu.umkc.permitme.domain.permit.ExcavationPermit;

@Service
public class ExcavationPermitService {
	
	public ExcavationPermit getExcavationPermit(Long projectId) {
	    ExcavationPermit excavation = new ExcavationPermit();
	    excavation.setApplicantName("Joel");
	    excavation.setApplicantEmail("joelwells@icloud.com");
	    excavation.setBore("4");
	    excavation.setBusinessAddress("7401 Main Street");
	    excavation.setCityStateZip("Kansas City, MO 64114");
	    excavation.setContactPhone("816-555-1212");
	    excavation.setFax("N/A");
	    excavation.setResponsibleFirm("ABC Plumbing, Inc.");
	    excavation.setJobAddress("10018 Briar St, Overland Park, KS 66207");
	    excavation.setPhone("816-555-1212");
	    excavation.setWorkDescription("1 hole, 4 feet long, 4 feet wide and 6 feet deep. Needed to connect a sanitary sewer.");
	    excavation.setSuperintendent("Buddy Holly");        
		return excavation;
	}

}
