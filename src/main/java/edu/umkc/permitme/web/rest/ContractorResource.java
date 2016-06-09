package edu.umkc.permitme.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.umkc.permitme.domain.Contractor;
import edu.umkc.permitme.domain.User;
import edu.umkc.permitme.repository.ContractorRepository;
import edu.umkc.permitme.repository.UserRepository;
import edu.umkc.permitme.security.SecurityUtils;
import edu.umkc.permitme.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Contractor.
 */
@RestController
@RequestMapping("/api")
public class ContractorResource {

    private final Logger log = LoggerFactory.getLogger(ContractorResource.class);
        
    @Inject
    private ContractorRepository contractorRepository;
    
    @Inject
    private UserRepository userRepository;
    
    /**
     * POST  /contractors : Create a new contractor.
     *
     * @param contractor the contractor to create
     * @return the ResponseEntity with status 201 (Created) and with body the new contractor, or with status 400 (Bad Request) if the contractor has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/contractors",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contractor> createContractor(@RequestBody Contractor contractor) throws URISyntaxException {
        log.debug("REST request to save Contractor : {}", contractor);
        if (contractor.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("contractor", "idexists", "A new contractor cannot already have an ID")).body(null);
        }
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin());
        contractor.setUser(user.get());
        
        Contractor result = contractorRepository.save(contractor);
        return ResponseEntity.created(new URI("/api/contractors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("contractor", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /contractors : Updates an existing contractor.
     *
     * @param contractor the contractor to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated contractor,
     * or with status 400 (Bad Request) if the contractor is not valid,
     * or with status 500 (Internal Server Error) if the contractor couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/contractors",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contractor> updateContractor(@RequestBody Contractor contractor) throws URISyntaxException {
        log.debug("REST request to update Contractor : {}", contractor);
        if (contractor.getId() == null) {
            return createContractor(contractor);
        }
        Contractor result = contractorRepository.save(contractor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("contractor", contractor.getId().toString()))
            .body(result);
    }

    /**
     * GET  /contractors : get all the contractors.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of contractors in body
     */
    @RequestMapping(value = "/contractors",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Contractor> getAllContractors() {
        log.debug("REST request to get all Contractors");
        List<Contractor> contractors = contractorRepository.findAll();
        return contractors;
    }

    /**
     * GET  /contractors/:id : get the "id" contractor.
     *
     * @param id the id of the contractor to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the contractor, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/contractors/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contractor> getContractor(@PathVariable Long id) {
        log.debug("REST request to get Contractor : {}", id);
        Contractor contractor = contractorRepository.findOne(id);
        return Optional.ofNullable(contractor)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /contractors/user : get the contractor the current user.
     *
     * @return the ResponseEntity with status 200 (OK) and with body the contractor, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/contractors/user",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Contractor> getContractorForUser() {
        log.debug("REST request to get Contractor for User");
        List<Contractor> contractors = contractorRepository.findByUserIsCurrentUser();
        Contractor contractor = null;
        if(contractors.size() > 0) {
        	Iterator<Contractor> iter = contractors.iterator();
        	contractor = iter.next();
        }
        return Optional.ofNullable(contractor)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

   
    /**
     * DELETE  /contractors/:id : delete the "id" contractor.
     *
     * @param id the id of the contractor to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/contractors/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteContractor(@PathVariable Long id) {
        log.debug("REST request to delete Contractor : {}", id);
        contractorRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("contractor", id.toString())).build();
    }

}
