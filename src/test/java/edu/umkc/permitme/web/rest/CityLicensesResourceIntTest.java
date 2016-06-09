package edu.umkc.permitme.web.rest;

import edu.umkc.permitme.PermitmeApp;
import edu.umkc.permitme.domain.CityLicenses;
import edu.umkc.permitme.repository.CityLicensesRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CityLicensesResource REST controller.
 *
 * @see CityLicensesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PermitmeApp.class)
@WebAppConfiguration
@IntegrationTest
public class CityLicensesResourceIntTest {

    private static final String DEFAULT_COMPANY_NAME = "AAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBB";
    private static final String DEFAULT_BUSINESS_ADDRESS = "AAAAA";
    private static final String UPDATED_BUSINESS_ADDRESS = "BBBBB";
    private static final String DEFAULT_CITY = "AAAAA";
    private static final String UPDATED_CITY = "BBBBB";
    private static final String DEFAULT_STATE = "AAAAA";
    private static final String UPDATED_STATE = "BBBBB";
    private static final String DEFAULT_ZIP_CODE = "AAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBB";
    private static final String DEFAULT_BUSINESS_PHONE = "AAAAA";
    private static final String UPDATED_BUSINESS_PHONE = "BBBBB";
    private static final String DEFAULT_CONTRACTOR_LICENSE_NUMBER = "AAAAA";
    private static final String UPDATED_CONTRACTOR_LICENSE_NUMBER = "BBBBB";
    private static final String DEFAULT_LICENSE_EXPIRATION_DATE = "AAAAA";
    private static final String UPDATED_LICENSE_EXPIRATION_DATE = "BBBBB";
    private static final String DEFAULT_SUPERVISOR_LAST_NAME = "AAAAA";
    private static final String UPDATED_SUPERVISOR_LAST_NAME = "BBBBB";
    private static final String DEFAULT_SUPERVISOR_FIRST_NAME = "AAAAA";
    private static final String UPDATED_SUPERVISOR_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_SUPERVISOR_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_SUPERVISOR_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER = "AAAAA";
    private static final String UPDATED_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER = "BBBBB";
    private static final String DEFAULT_CERTIFICATE_EXPIRATION_DATE = "AAAAA";
    private static final String UPDATED_CERTIFICATE_EXPIRATION_DATE = "BBBBB";
    private static final String DEFAULT_BUSINESS_LICENSE_EXPIRATION_DATE = "AAAAA";
    private static final String UPDATED_BUSINESS_LICENSE_EXPIRATION_DATE = "BBBBB";

    @Inject
    private CityLicensesRepository cityLicensesRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCityLicensesMockMvc;

    private CityLicenses cityLicenses;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CityLicensesResource cityLicensesResource = new CityLicensesResource();
        ReflectionTestUtils.setField(cityLicensesResource, "cityLicensesRepository", cityLicensesRepository);
        this.restCityLicensesMockMvc = MockMvcBuilders.standaloneSetup(cityLicensesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cityLicenses = new CityLicenses();
        cityLicenses.setCompany_name(DEFAULT_COMPANY_NAME);
        cityLicenses.setBusiness_address(DEFAULT_BUSINESS_ADDRESS);
        cityLicenses.setCity(DEFAULT_CITY);
        cityLicenses.setState(DEFAULT_STATE);
        cityLicenses.setZip_code(DEFAULT_ZIP_CODE);
        cityLicenses.setBusiness_phone(DEFAULT_BUSINESS_PHONE);
        cityLicenses.setContractor_license_number(DEFAULT_CONTRACTOR_LICENSE_NUMBER);
        cityLicenses.setLicense_expiration_date(DEFAULT_LICENSE_EXPIRATION_DATE);
        cityLicenses.setSupervisor_last_name(DEFAULT_SUPERVISOR_LAST_NAME);
        cityLicenses.setSupervisor_first_name(DEFAULT_SUPERVISOR_FIRST_NAME);
        cityLicenses.setSupervisor_middle_name(DEFAULT_SUPERVISOR_MIDDLE_NAME);
        cityLicenses.setMaster_craftsman_certificate_number(DEFAULT_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER);
        cityLicenses.setCertificate_expiration_date(DEFAULT_CERTIFICATE_EXPIRATION_DATE);
        cityLicenses.setBusiness_license_expiration_date(DEFAULT_BUSINESS_LICENSE_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void createCityLicenses() throws Exception {
        int databaseSizeBeforeCreate = cityLicensesRepository.findAll().size();

        // Create the CityLicenses

        restCityLicensesMockMvc.perform(post("/api/city-licenses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cityLicenses)))
                .andExpect(status().isCreated());

        // Validate the CityLicenses in the database
        List<CityLicenses> cityLicenses = cityLicensesRepository.findAll();
        assertThat(cityLicenses).hasSize(databaseSizeBeforeCreate + 1);
        CityLicenses testCityLicenses = cityLicenses.get(cityLicenses.size() - 1);
        assertThat(testCityLicenses.getCompany_name()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testCityLicenses.getBusiness_address()).isEqualTo(DEFAULT_BUSINESS_ADDRESS);
        assertThat(testCityLicenses.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCityLicenses.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testCityLicenses.getZip_code()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testCityLicenses.getBusiness_phone()).isEqualTo(DEFAULT_BUSINESS_PHONE);
        assertThat(testCityLicenses.getContractor_license_number()).isEqualTo(DEFAULT_CONTRACTOR_LICENSE_NUMBER);
        assertThat(testCityLicenses.getLicense_expiration_date()).isEqualTo(DEFAULT_LICENSE_EXPIRATION_DATE);
        assertThat(testCityLicenses.getSupervisor_last_name()).isEqualTo(DEFAULT_SUPERVISOR_LAST_NAME);
        assertThat(testCityLicenses.getSupervisor_first_name()).isEqualTo(DEFAULT_SUPERVISOR_FIRST_NAME);
        assertThat(testCityLicenses.getSupervisor_middle_name()).isEqualTo(DEFAULT_SUPERVISOR_MIDDLE_NAME);
        assertThat(testCityLicenses.getMaster_craftsman_certificate_number()).isEqualTo(DEFAULT_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER);
        assertThat(testCityLicenses.getCertificate_expiration_date()).isEqualTo(DEFAULT_CERTIFICATE_EXPIRATION_DATE);
        assertThat(testCityLicenses.getBusiness_license_expiration_date()).isEqualTo(DEFAULT_BUSINESS_LICENSE_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void getAllCityLicenses() throws Exception {
        // Initialize the database
        cityLicensesRepository.saveAndFlush(cityLicenses);

        // Get all the cityLicenses
        restCityLicensesMockMvc.perform(get("/api/city-licenses?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cityLicenses.getId().intValue())))
                .andExpect(jsonPath("$.[*].company_name").value(hasItem(DEFAULT_COMPANY_NAME.toString())))
                .andExpect(jsonPath("$.[*].business_address").value(hasItem(DEFAULT_BUSINESS_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
                .andExpect(jsonPath("$.[*].zip_code").value(hasItem(DEFAULT_ZIP_CODE.toString())))
                .andExpect(jsonPath("$.[*].business_phone").value(hasItem(DEFAULT_BUSINESS_PHONE.toString())))
                .andExpect(jsonPath("$.[*].contractor_license_number").value(hasItem(DEFAULT_CONTRACTOR_LICENSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].license_expiration_date").value(hasItem(DEFAULT_LICENSE_EXPIRATION_DATE.toString())))
                .andExpect(jsonPath("$.[*].supervisor_last_name").value(hasItem(DEFAULT_SUPERVISOR_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].supervisor_first_name").value(hasItem(DEFAULT_SUPERVISOR_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].supervisor_middle_name").value(hasItem(DEFAULT_SUPERVISOR_MIDDLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].master_craftsman_certificate_number").value(hasItem(DEFAULT_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].certificate_expiration_date").value(hasItem(DEFAULT_CERTIFICATE_EXPIRATION_DATE.toString())))
                .andExpect(jsonPath("$.[*].business_license_expiration_date").value(hasItem(DEFAULT_BUSINESS_LICENSE_EXPIRATION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getCityLicenses() throws Exception {
        // Initialize the database
        cityLicensesRepository.saveAndFlush(cityLicenses);

        // Get the cityLicenses
        restCityLicensesMockMvc.perform(get("/api/city-licenses/{id}", cityLicenses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cityLicenses.getId().intValue()))
            .andExpect(jsonPath("$.company_name").value(DEFAULT_COMPANY_NAME.toString()))
            .andExpect(jsonPath("$.business_address").value(DEFAULT_BUSINESS_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.zip_code").value(DEFAULT_ZIP_CODE.toString()))
            .andExpect(jsonPath("$.business_phone").value(DEFAULT_BUSINESS_PHONE.toString()))
            .andExpect(jsonPath("$.contractor_license_number").value(DEFAULT_CONTRACTOR_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.license_expiration_date").value(DEFAULT_LICENSE_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.supervisor_last_name").value(DEFAULT_SUPERVISOR_LAST_NAME.toString()))
            .andExpect(jsonPath("$.supervisor_first_name").value(DEFAULT_SUPERVISOR_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.supervisor_middle_name").value(DEFAULT_SUPERVISOR_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.master_craftsman_certificate_number").value(DEFAULT_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER.toString()))
            .andExpect(jsonPath("$.certificate_expiration_date").value(DEFAULT_CERTIFICATE_EXPIRATION_DATE.toString()))
            .andExpect(jsonPath("$.business_license_expiration_date").value(DEFAULT_BUSINESS_LICENSE_EXPIRATION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCityLicenses() throws Exception {
        // Get the cityLicenses
        restCityLicensesMockMvc.perform(get("/api/city-licenses/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCityLicenses() throws Exception {
        // Initialize the database
        cityLicensesRepository.saveAndFlush(cityLicenses);
        int databaseSizeBeforeUpdate = cityLicensesRepository.findAll().size();

        // Update the cityLicenses
        CityLicenses updatedCityLicenses = new CityLicenses();
        updatedCityLicenses.setId(cityLicenses.getId());
        updatedCityLicenses.setCompany_name(UPDATED_COMPANY_NAME);
        updatedCityLicenses.setBusiness_address(UPDATED_BUSINESS_ADDRESS);
        updatedCityLicenses.setCity(UPDATED_CITY);
        updatedCityLicenses.setState(UPDATED_STATE);
        updatedCityLicenses.setZip_code(UPDATED_ZIP_CODE);
        updatedCityLicenses.setBusiness_phone(UPDATED_BUSINESS_PHONE);
        updatedCityLicenses.setContractor_license_number(UPDATED_CONTRACTOR_LICENSE_NUMBER);
        updatedCityLicenses.setLicense_expiration_date(UPDATED_LICENSE_EXPIRATION_DATE);
        updatedCityLicenses.setSupervisor_last_name(UPDATED_SUPERVISOR_LAST_NAME);
        updatedCityLicenses.setSupervisor_first_name(UPDATED_SUPERVISOR_FIRST_NAME);
        updatedCityLicenses.setSupervisor_middle_name(UPDATED_SUPERVISOR_MIDDLE_NAME);
        updatedCityLicenses.setMaster_craftsman_certificate_number(UPDATED_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER);
        updatedCityLicenses.setCertificate_expiration_date(UPDATED_CERTIFICATE_EXPIRATION_DATE);
        updatedCityLicenses.setBusiness_license_expiration_date(UPDATED_BUSINESS_LICENSE_EXPIRATION_DATE);

        restCityLicensesMockMvc.perform(put("/api/city-licenses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedCityLicenses)))
                .andExpect(status().isOk());

        // Validate the CityLicenses in the database
        List<CityLicenses> cityLicenses = cityLicensesRepository.findAll();
        assertThat(cityLicenses).hasSize(databaseSizeBeforeUpdate);
        CityLicenses testCityLicenses = cityLicenses.get(cityLicenses.size() - 1);
        assertThat(testCityLicenses.getCompany_name()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testCityLicenses.getBusiness_address()).isEqualTo(UPDATED_BUSINESS_ADDRESS);
        assertThat(testCityLicenses.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCityLicenses.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testCityLicenses.getZip_code()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testCityLicenses.getBusiness_phone()).isEqualTo(UPDATED_BUSINESS_PHONE);
        assertThat(testCityLicenses.getContractor_license_number()).isEqualTo(UPDATED_CONTRACTOR_LICENSE_NUMBER);
        assertThat(testCityLicenses.getLicense_expiration_date()).isEqualTo(UPDATED_LICENSE_EXPIRATION_DATE);
        assertThat(testCityLicenses.getSupervisor_last_name()).isEqualTo(UPDATED_SUPERVISOR_LAST_NAME);
        assertThat(testCityLicenses.getSupervisor_first_name()).isEqualTo(UPDATED_SUPERVISOR_FIRST_NAME);
        assertThat(testCityLicenses.getSupervisor_middle_name()).isEqualTo(UPDATED_SUPERVISOR_MIDDLE_NAME);
        assertThat(testCityLicenses.getMaster_craftsman_certificate_number()).isEqualTo(UPDATED_MASTER_CRAFTSMAN_CERTIFICATE_NUMBER);
        assertThat(testCityLicenses.getCertificate_expiration_date()).isEqualTo(UPDATED_CERTIFICATE_EXPIRATION_DATE);
        assertThat(testCityLicenses.getBusiness_license_expiration_date()).isEqualTo(UPDATED_BUSINESS_LICENSE_EXPIRATION_DATE);
    }

    @Test
    @Transactional
    public void deleteCityLicenses() throws Exception {
        // Initialize the database
        cityLicensesRepository.saveAndFlush(cityLicenses);
        int databaseSizeBeforeDelete = cityLicensesRepository.findAll().size();

        // Get the cityLicenses
        restCityLicensesMockMvc.perform(delete("/api/city-licenses/{id}", cityLicenses.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CityLicenses> cityLicenses = cityLicensesRepository.findAll();
        assertThat(cityLicenses).hasSize(databaseSizeBeforeDelete - 1);
    }
}
