package edu.umkc.permitme.web.rest;

import com.codahale.metrics.annotation.Timed;
import edu.umkc.permitme.domain.CityLicenses;
import edu.umkc.permitme.repository.CityLicensesRepository;
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
 * REST controller for managing CityLicenses.
 */
@RestController
@RequestMapping("/api")
public class CityLicensesResource {

    private final Logger log = LoggerFactory.getLogger(CityLicensesResource.class);
        
    @Inject
    private CityLicensesRepository cityLicensesRepository;
    
    /**
     * POST  /city-licenses : Create a new cityLicenses.
     *
     * @param cityLicenses the cityLicenses to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cityLicenses, or with status 400 (Bad Request) if the cityLicenses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/city-licenses",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CityLicenses> createCityLicenses(@RequestBody CityLicenses cityLicenses) throws URISyntaxException {
        log.debug("REST request to save CityLicenses : {}", cityLicenses);
        if (cityLicenses.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cityLicenses", "idexists", "A new cityLicenses cannot already have an ID")).body(null);
        }
        CityLicenses result = cityLicensesRepository.save(cityLicenses);
        return ResponseEntity.created(new URI("/api/city-licenses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cityLicenses", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /city-licenses : Updates an existing cityLicenses.
     *
     * @param cityLicenses the cityLicenses to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cityLicenses,
     * or with status 400 (Bad Request) if the cityLicenses is not valid,
     * or with status 500 (Internal Server Error) if the cityLicenses couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/city-licenses",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CityLicenses> updateCityLicenses(@RequestBody CityLicenses cityLicenses) throws URISyntaxException {
        log.debug("REST request to update CityLicenses : {}", cityLicenses);
        if (cityLicenses.getId() == null) {
            return createCityLicenses(cityLicenses);
        }
        CityLicenses result = cityLicensesRepository.save(cityLicenses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cityLicenses", cityLicenses.getId().toString()))
            .body(result);
    }

    /**
     * GET  /city-licenses : get all the cityLicenses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of cityLicenses in body
     */
    @RequestMapping(value = "/city-licenses",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<CityLicenses> getAllCityLicenses() {
        log.debug("REST request to get all CityLicenses");
        List<CityLicenses> cityLicenses = cityLicensesRepository.findAll();
        return cityLicenses;
    }

    /**
     * GET  /city-licenses/:id : get the "id" cityLicenses.
     *
     * @param id the id of the cityLicenses to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cityLicenses, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/city-licenses/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CityLicenses> getCityLicenses(@PathVariable Long id) {
        log.debug("REST request to get CityLicenses : {}", id);
        CityLicenses cityLicenses = cityLicensesRepository.findOne(id);
        return Optional.ofNullable(cityLicenses)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /city-licenses/:id : delete the "id" cityLicenses.
     *
     * @param id the id of the cityLicenses to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/city-licenses/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCityLicenses(@PathVariable Long id) {
        log.debug("REST request to delete CityLicenses : {}", id);
        cityLicensesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cityLicenses", id.toString())).build();
    }

}
