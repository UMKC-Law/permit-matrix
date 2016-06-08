package edu.umkc.permitme.web.rest;

import edu.umkc.permitme.PermitmeApp;
import edu.umkc.permitme.domain.Contractor;
import edu.umkc.permitme.repository.ContractorRepository;

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
 * Test class for the ContractorResource REST controller.
 *
 * @see ContractorResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PermitmeApp.class)
@WebAppConfiguration
@IntegrationTest
public class ContractorResourceIntTest {

    private static final String DEFAULT_BUSINESS_NAME = "AAAAA";
    private static final String UPDATED_BUSINESS_NAME = "BBBBB";
    private static final String DEFAULT_BUSINESS_LICENSE_NUMBER = "AAAAA";
    private static final String UPDATED_BUSINESS_LICENSE_NUMBER = "BBBBB";
    private static final String DEFAULT_PHONE_NUMBER = "AAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBB";
    private static final String DEFAULT_STREET_ADDRESS = "AAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBB";
    private static final String DEFAULT_ZIP_CODE = "AAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBB";
    private static final String DEFAULT_CITY = "AAAAA";
    private static final String UPDATED_CITY = "BBBBB";
    private static final String DEFAULT_STATE = "AAAAA";
    private static final String UPDATED_STATE = "BBBBB";
    private static final String DEFAULT_AGENT_FIRST_NAME = "AAAAA";
    private static final String UPDATED_AGENT_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_AGENT_MIDDLE_INITIAL = "AAAAA";
    private static final String UPDATED_AGENT_MIDDLE_INITIAL = "BBBBB";
    private static final String DEFAULT_AGENT_LAST_NAME = "AAAAA";
    private static final String UPDATED_AGENT_LAST_NAME = "BBBBB";
    private static final String DEFAULT_AGENT_JOB_TITLE = "AAAAA";
    private static final String UPDATED_AGENT_JOB_TITLE = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";
    private static final String DEFAULT_AGENT_PHONE_NUMBER = "AAAAA";
    private static final String UPDATED_AGENT_PHONE_NUMBER = "BBBBB";
    private static final String DEFAULT_CONTRACT_LICENSE_NUMBER = "AAAAA";
    private static final String UPDATED_CONTRACT_LICENSE_NUMBER = "BBBBB";
    private static final String DEFAULT_OCCUPATIONAL_LICENSE_NUMBER = "AAAAA";
    private static final String UPDATED_OCCUPATIONAL_LICENSE_NUMBER = "BBBBB";
    private static final String DEFAULT_MASTER_PLUMBER_LICENSE_NUMBER = "AAAAA";
    private static final String UPDATED_MASTER_PLUMBER_LICENSE_NUMBER = "BBBBB";

    private static final Boolean DEFAULT_HAS_GENERAL_LIABILITY_COVERAGE = false;
    private static final Boolean UPDATED_HAS_GENERAL_LIABILITY_COVERAGE = true;

    private static final Boolean DEFAULT_REQUIREMENTS_MET = false;
    private static final Boolean UPDATED_REQUIREMENTS_MET = true;
    private static final String DEFAULT_CARRIER = "AAAAA";
    private static final String UPDATED_CARRIER = "BBBBB";
    private static final String DEFAULT_POLICY_NUMBER = "AAAAA";
    private static final String UPDATED_POLICY_NUMBER = "BBBBB";

    @Inject
    private ContractorRepository contractorRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restContractorMockMvc;

    private Contractor contractor;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ContractorResource contractorResource = new ContractorResource();
        ReflectionTestUtils.setField(contractorResource, "contractorRepository", contractorRepository);
        this.restContractorMockMvc = MockMvcBuilders.standaloneSetup(contractorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        contractor = new Contractor();
        contractor.setBusinessName(DEFAULT_BUSINESS_NAME);
        contractor.setBusinessLicenseNumber(DEFAULT_BUSINESS_LICENSE_NUMBER);
        contractor.setPhoneNumber(DEFAULT_PHONE_NUMBER);
        contractor.setStreetAddress(DEFAULT_STREET_ADDRESS);
        contractor.setZipCode(DEFAULT_ZIP_CODE);
        contractor.setCity(DEFAULT_CITY);
        contractor.setState(DEFAULT_STATE);
        contractor.setAgentFirstName(DEFAULT_AGENT_FIRST_NAME);
        contractor.setAgentMiddleInitial(DEFAULT_AGENT_MIDDLE_INITIAL);
        contractor.setAgentLastName(DEFAULT_AGENT_LAST_NAME);
        contractor.setAgentJobTitle(DEFAULT_AGENT_JOB_TITLE);
        contractor.setEmail(DEFAULT_EMAIL);
        contractor.setAgentPhoneNumber(DEFAULT_AGENT_PHONE_NUMBER);
        contractor.setContractLicenseNumber(DEFAULT_CONTRACT_LICENSE_NUMBER);
        contractor.setOccupationalLicenseNumber(DEFAULT_OCCUPATIONAL_LICENSE_NUMBER);
        contractor.setMasterPlumberLicenseNumber(DEFAULT_MASTER_PLUMBER_LICENSE_NUMBER);
        contractor.setHasGeneralLiabilityCoverage(DEFAULT_HAS_GENERAL_LIABILITY_COVERAGE);
        contractor.setRequirementsMet(DEFAULT_REQUIREMENTS_MET);
        contractor.setCarrier(DEFAULT_CARRIER);
        contractor.setPolicyNumber(DEFAULT_POLICY_NUMBER);
    }

    @Test
    @Transactional
    public void createContractor() throws Exception {
        int databaseSizeBeforeCreate = contractorRepository.findAll().size();

        // Create the Contractor

        restContractorMockMvc.perform(post("/api/contractors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(contractor)))
                .andExpect(status().isCreated());

        // Validate the Contractor in the database
        List<Contractor> contractors = contractorRepository.findAll();
        assertThat(contractors).hasSize(databaseSizeBeforeCreate + 1);
        Contractor testContractor = contractors.get(contractors.size() - 1);
        assertThat(testContractor.getBusinessName()).isEqualTo(DEFAULT_BUSINESS_NAME);
        assertThat(testContractor.getBusinessLicenseNumber()).isEqualTo(DEFAULT_BUSINESS_LICENSE_NUMBER);
        assertThat(testContractor.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testContractor.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testContractor.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testContractor.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testContractor.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testContractor.getAgentFirstName()).isEqualTo(DEFAULT_AGENT_FIRST_NAME);
        assertThat(testContractor.getAgentMiddleInitial()).isEqualTo(DEFAULT_AGENT_MIDDLE_INITIAL);
        assertThat(testContractor.getAgentLastName()).isEqualTo(DEFAULT_AGENT_LAST_NAME);
        assertThat(testContractor.getAgentJobTitle()).isEqualTo(DEFAULT_AGENT_JOB_TITLE);
        assertThat(testContractor.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testContractor.getAgentPhoneNumber()).isEqualTo(DEFAULT_AGENT_PHONE_NUMBER);
        assertThat(testContractor.getContractLicenseNumber()).isEqualTo(DEFAULT_CONTRACT_LICENSE_NUMBER);
        assertThat(testContractor.getOccupationalLicenseNumber()).isEqualTo(DEFAULT_OCCUPATIONAL_LICENSE_NUMBER);
        assertThat(testContractor.getMasterPlumberLicenseNumber()).isEqualTo(DEFAULT_MASTER_PLUMBER_LICENSE_NUMBER);
        assertThat(testContractor.isHasGeneralLiabilityCoverage()).isEqualTo(DEFAULT_HAS_GENERAL_LIABILITY_COVERAGE);
        assertThat(testContractor.isRequirementsMet()).isEqualTo(DEFAULT_REQUIREMENTS_MET);
        assertThat(testContractor.getCarrier()).isEqualTo(DEFAULT_CARRIER);
        assertThat(testContractor.getPolicyNumber()).isEqualTo(DEFAULT_POLICY_NUMBER);
    }

    @Test
    @Transactional
    public void getAllContractors() throws Exception {
        // Initialize the database
        contractorRepository.saveAndFlush(contractor);

        // Get all the contractors
        restContractorMockMvc.perform(get("/api/contractors?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(contractor.getId().intValue())))
                .andExpect(jsonPath("$.[*].businessName").value(hasItem(DEFAULT_BUSINESS_NAME.toString())))
                .andExpect(jsonPath("$.[*].businessLicenseNumber").value(hasItem(DEFAULT_BUSINESS_LICENSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
                .andExpect(jsonPath("$.[*].agentFirstName").value(hasItem(DEFAULT_AGENT_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].agentMiddleInitial").value(hasItem(DEFAULT_AGENT_MIDDLE_INITIAL.toString())))
                .andExpect(jsonPath("$.[*].agentLastName").value(hasItem(DEFAULT_AGENT_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].agentJobTitle").value(hasItem(DEFAULT_AGENT_JOB_TITLE.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].agentPhoneNumber").value(hasItem(DEFAULT_AGENT_PHONE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].contractLicenseNumber").value(hasItem(DEFAULT_CONTRACT_LICENSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].occupationalLicenseNumber").value(hasItem(DEFAULT_OCCUPATIONAL_LICENSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].masterPlumberLicenseNumber").value(hasItem(DEFAULT_MASTER_PLUMBER_LICENSE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].hasGeneralLiabilityCoverage").value(hasItem(DEFAULT_HAS_GENERAL_LIABILITY_COVERAGE.booleanValue())))
                .andExpect(jsonPath("$.[*].requirementsMet").value(hasItem(DEFAULT_REQUIREMENTS_MET.booleanValue())))
                .andExpect(jsonPath("$.[*].carrier").value(hasItem(DEFAULT_CARRIER.toString())))
                .andExpect(jsonPath("$.[*].policyNumber").value(hasItem(DEFAULT_POLICY_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getContractor() throws Exception {
        // Initialize the database
        contractorRepository.saveAndFlush(contractor);

        // Get the contractor
        restContractorMockMvc.perform(get("/api/contractors/{id}", contractor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(contractor.getId().intValue()))
            .andExpect(jsonPath("$.businessName").value(DEFAULT_BUSINESS_NAME.toString()))
            .andExpect(jsonPath("$.businessLicenseNumber").value(DEFAULT_BUSINESS_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.streetAddress").value(DEFAULT_STREET_ADDRESS.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.agentFirstName").value(DEFAULT_AGENT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.agentMiddleInitial").value(DEFAULT_AGENT_MIDDLE_INITIAL.toString()))
            .andExpect(jsonPath("$.agentLastName").value(DEFAULT_AGENT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.agentJobTitle").value(DEFAULT_AGENT_JOB_TITLE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.agentPhoneNumber").value(DEFAULT_AGENT_PHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.contractLicenseNumber").value(DEFAULT_CONTRACT_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.occupationalLicenseNumber").value(DEFAULT_OCCUPATIONAL_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.masterPlumberLicenseNumber").value(DEFAULT_MASTER_PLUMBER_LICENSE_NUMBER.toString()))
            .andExpect(jsonPath("$.hasGeneralLiabilityCoverage").value(DEFAULT_HAS_GENERAL_LIABILITY_COVERAGE.booleanValue()))
            .andExpect(jsonPath("$.requirementsMet").value(DEFAULT_REQUIREMENTS_MET.booleanValue()))
            .andExpect(jsonPath("$.carrier").value(DEFAULT_CARRIER.toString()))
            .andExpect(jsonPath("$.policyNumber").value(DEFAULT_POLICY_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContractor() throws Exception {
        // Get the contractor
        restContractorMockMvc.perform(get("/api/contractors/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContractor() throws Exception {
        // Initialize the database
        contractorRepository.saveAndFlush(contractor);
        int databaseSizeBeforeUpdate = contractorRepository.findAll().size();

        // Update the contractor
        Contractor updatedContractor = new Contractor();
        updatedContractor.setId(contractor.getId());
        updatedContractor.setBusinessName(UPDATED_BUSINESS_NAME);
        updatedContractor.setBusinessLicenseNumber(UPDATED_BUSINESS_LICENSE_NUMBER);
        updatedContractor.setPhoneNumber(UPDATED_PHONE_NUMBER);
        updatedContractor.setStreetAddress(UPDATED_STREET_ADDRESS);
        updatedContractor.setZipCode(UPDATED_ZIP_CODE);
        updatedContractor.setCity(UPDATED_CITY);
        updatedContractor.setState(UPDATED_STATE);
        updatedContractor.setAgentFirstName(UPDATED_AGENT_FIRST_NAME);
        updatedContractor.setAgentMiddleInitial(UPDATED_AGENT_MIDDLE_INITIAL);
        updatedContractor.setAgentLastName(UPDATED_AGENT_LAST_NAME);
        updatedContractor.setAgentJobTitle(UPDATED_AGENT_JOB_TITLE);
        updatedContractor.setEmail(UPDATED_EMAIL);
        updatedContractor.setAgentPhoneNumber(UPDATED_AGENT_PHONE_NUMBER);
        updatedContractor.setContractLicenseNumber(UPDATED_CONTRACT_LICENSE_NUMBER);
        updatedContractor.setOccupationalLicenseNumber(UPDATED_OCCUPATIONAL_LICENSE_NUMBER);
        updatedContractor.setMasterPlumberLicenseNumber(UPDATED_MASTER_PLUMBER_LICENSE_NUMBER);
        updatedContractor.setHasGeneralLiabilityCoverage(UPDATED_HAS_GENERAL_LIABILITY_COVERAGE);
        updatedContractor.setRequirementsMet(UPDATED_REQUIREMENTS_MET);
        updatedContractor.setCarrier(UPDATED_CARRIER);
        updatedContractor.setPolicyNumber(UPDATED_POLICY_NUMBER);

        restContractorMockMvc.perform(put("/api/contractors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedContractor)))
                .andExpect(status().isOk());

        // Validate the Contractor in the database
        List<Contractor> contractors = contractorRepository.findAll();
        assertThat(contractors).hasSize(databaseSizeBeforeUpdate);
        Contractor testContractor = contractors.get(contractors.size() - 1);
        assertThat(testContractor.getBusinessName()).isEqualTo(UPDATED_BUSINESS_NAME);
        assertThat(testContractor.getBusinessLicenseNumber()).isEqualTo(UPDATED_BUSINESS_LICENSE_NUMBER);
        assertThat(testContractor.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testContractor.getStreetAddress()).isEqualTo(UPDATED_STREET_ADDRESS);
        assertThat(testContractor.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testContractor.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testContractor.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testContractor.getAgentFirstName()).isEqualTo(UPDATED_AGENT_FIRST_NAME);
        assertThat(testContractor.getAgentMiddleInitial()).isEqualTo(UPDATED_AGENT_MIDDLE_INITIAL);
        assertThat(testContractor.getAgentLastName()).isEqualTo(UPDATED_AGENT_LAST_NAME);
        assertThat(testContractor.getAgentJobTitle()).isEqualTo(UPDATED_AGENT_JOB_TITLE);
        assertThat(testContractor.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testContractor.getAgentPhoneNumber()).isEqualTo(UPDATED_AGENT_PHONE_NUMBER);
        assertThat(testContractor.getContractLicenseNumber()).isEqualTo(UPDATED_CONTRACT_LICENSE_NUMBER);
        assertThat(testContractor.getOccupationalLicenseNumber()).isEqualTo(UPDATED_OCCUPATIONAL_LICENSE_NUMBER);
        assertThat(testContractor.getMasterPlumberLicenseNumber()).isEqualTo(UPDATED_MASTER_PLUMBER_LICENSE_NUMBER);
        assertThat(testContractor.isHasGeneralLiabilityCoverage()).isEqualTo(UPDATED_HAS_GENERAL_LIABILITY_COVERAGE);
        assertThat(testContractor.isRequirementsMet()).isEqualTo(UPDATED_REQUIREMENTS_MET);
        assertThat(testContractor.getCarrier()).isEqualTo(UPDATED_CARRIER);
        assertThat(testContractor.getPolicyNumber()).isEqualTo(UPDATED_POLICY_NUMBER);
    }

    @Test
    @Transactional
    public void deleteContractor() throws Exception {
        // Initialize the database
        contractorRepository.saveAndFlush(contractor);
        int databaseSizeBeforeDelete = contractorRepository.findAll().size();

        // Get the contractor
        restContractorMockMvc.perform(delete("/api/contractors/{id}", contractor.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Contractor> contractors = contractorRepository.findAll();
        assertThat(contractors).hasSize(databaseSizeBeforeDelete - 1);
    }
}
