package edu.umkc.permitme.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.umkc.permitme.domain.RightOfWay;
import edu.umkc.permitme.repository.RightOfWayRepository;
import edu.umkc.permitme.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing RightOfWay.
 */
@RestController
@RequestMapping("/api")
public class RightOfWayResource {

    private final Logger log = LoggerFactory.getLogger(RightOfWayResource.class);
        
    @Inject
    private RightOfWayRepository rightOfWayRepository;
    
    /**
     * POST  /right-of-ways : Create a new rightOfWay.
     *
     * @param rightOfWay the rightOfWay to create
     * @return the ResponseEntity with status 201 (Created) and with body the new rightOfWay, or with status 400 (Bad Request) if the rightOfWay has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/right-of-ways",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RightOfWay> createRightOfWay(@RequestBody RightOfWay rightOfWay) throws URISyntaxException {
        log.debug("REST request to save RightOfWay : {}", rightOfWay);
        if (rightOfWay.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("rightOfWay", "idexists", "A new rightOfWay cannot already have an ID")).body(null);
        }
        RightOfWay result = rightOfWayRepository.save(rightOfWay);
        return ResponseEntity.created(new URI("/api/right-of-ways/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("rightOfWay", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /right-of-ways : Updates an existing rightOfWay.
     *
     * @param rightOfWay the rightOfWay to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated rightOfWay,
     * or with status 400 (Bad Request) if the rightOfWay is not valid,
     * or with status 500 (Internal Server Error) if the rightOfWay couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/right-of-ways",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RightOfWay> updateRightOfWay(@RequestBody RightOfWay rightOfWay) throws URISyntaxException {
        log.debug("REST request to update RightOfWay : {}", rightOfWay);
        if (rightOfWay.getId() == null) {
            return createRightOfWay(rightOfWay);
        }
        RightOfWay result = rightOfWayRepository.save(rightOfWay);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("rightOfWay", rightOfWay.getId().toString()))
            .body(result);
    }

    /**
     * GET  /right-of-ways : get all the rightOfWays.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rightOfWays in body
     */
    @RequestMapping(value = "/right-of-ways",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<RightOfWay> getAllRightOfWays() {
        log.debug("REST request to get all RightOfWays");
        List<RightOfWay> rightOfWays = rightOfWayRepository.findAll();
        return rightOfWays;
    }

    /**
     * GET  /right-of-ways/:id : get the "id" rightOfWay.
     *
     * @param id the id of the rightOfWay to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the rightOfWay, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/right-of-ways/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RightOfWay> getRightOfWay(@PathVariable Long id) {
        log.debug("REST request to get RightOfWay : {}", id);
        RightOfWay rightOfWay = rightOfWayRepository.findOne(id);
        return Optional.ofNullable(rightOfWay)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /right-of-ways/:id : delete the "id" rightOfWay.
     *
     * @param id the id of the rightOfWay to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/right-of-ways/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRightOfWay(@PathVariable Long id) {
        log.debug("REST request to delete RightOfWay : {}", id);
        rightOfWayRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("rightOfWay", id.toString())).build();
    }

}
