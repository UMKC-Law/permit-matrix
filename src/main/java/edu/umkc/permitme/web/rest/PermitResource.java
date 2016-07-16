package edu.umkc.permitme.web.rest;

import java.io.ByteArrayOutputStream;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import edu.umkc.permitme.domain.permit.ExcavationPermit;
import edu.umkc.permitme.service.PdfRenderingService;
import edu.umkc.permitme.service.permit.ExcavationPermitService;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api/permits")
public class PermitResource {
	
    private final Logger log = LoggerFactory.getLogger(PermitResource.class);
    
    @Inject
    private ExcavationPermitService excavationService;
    @Inject PdfRenderingService pdfService;

    /**
     * GET  /excavation/{projectId} : gets the excavation permit application PDF for a project.
     *
     * @return the ResponseEntity with status 200 (OK) and the PDF of the completed permit application
     */
    @RequestMapping(value = "/excavation/{projectId}",
        method = RequestMethod.GET)
    @Timed
    public ResponseEntity<byte[]> getExcavationPermitApplication(@PathVariable Long projectId) throws JAXBException {
        log.debug("REST request to get the excavation permit application for project {}", projectId);
        ExcavationPermit permitModel = excavationService.getExcavationPermit(projectId);
        
        JAXBContext jaxbContext = JAXBContext.newInstance(ExcavationPermit.class);
        JAXBSource source = new JAXBSource(jaxbContext, permitModel);
        ByteArrayOutputStream pdfByteStream = pdfService.renderPdf(source, "excavation.xsl");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "excavation-permit-application.pdf";
        headers.add("content-disposition", "inline;filename=" + filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        return new ResponseEntity<byte[]>(pdfByteStream.toByteArray(), headers, HttpStatus.OK);
    }
    
}
