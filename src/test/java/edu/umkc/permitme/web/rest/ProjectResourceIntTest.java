package edu.umkc.permitme.web.rest;

import edu.umkc.permitme.PermitmeApp;
import edu.umkc.permitme.domain.Project;
import edu.umkc.permitme.repository.ProjectRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import edu.umkc.permitme.domain.enumeration.ProjectStatus;
import edu.umkc.permitme.domain.enumeration.RepairLocation;
import edu.umkc.permitme.domain.enumeration.MeterLocation;
import edu.umkc.permitme.domain.enumeration.PlumbingType;
import edu.umkc.permitme.domain.enumeration.WorkSiteType;

/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PermitmeApp.class)
@WebAppConfiguration
@IntegrationTest
public class ProjectResourceIntTest {

    private static final String DEFAULT_PROJECT_NAME = "AAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBB";
    private static final String DEFAULT_PROJECT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_PROJECT_DESCRIPTION = "BBBBB";

    private static final ProjectStatus DEFAULT_PROJECT_STATUS = ProjectStatus.INCOMPLETE;
    private static final ProjectStatus UPDATED_PROJECT_STATUS = ProjectStatus.READY;

    private static final LocalDate DEFAULT_ADDED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADDED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_SUBMITTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SUBMITTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_IS_WORK_ON_EXISTING_PLUMBING = false;
    private static final Boolean UPDATED_IS_WORK_ON_EXISTING_PLUMBING = true;

    private static final RepairLocation DEFAULT_REPAIR_LOCATION = RepairLocation.CURB_BOX;
    private static final RepairLocation UPDATED_REPAIR_LOCATION = RepairLocation.METER_TILE;
    private static final String DEFAULT_CUSTOM_REPAIR_LOCATION = "AAAAA";
    private static final String UPDATED_CUSTOM_REPAIR_LOCATION = "BBBBB";

    private static final MeterLocation DEFAULT_METER_LOCATION = MeterLocation.INSIDE;
    private static final MeterLocation UPDATED_METER_LOCATION = MeterLocation.OUTSIDE;

    private static final Boolean DEFAULT_IS_NEW_LINE = false;
    private static final Boolean UPDATED_IS_NEW_LINE = true;

    private static final PlumbingType DEFAULT_PLUMBING_TYPE = PlumbingType.WATER;
    private static final PlumbingType UPDATED_PLUMBING_TYPE = PlumbingType.TAP;
    private static final String DEFAULT_CUSTOM_PLUMBING_TYPE = "AAAAA";
    private static final String UPDATED_CUSTOM_PLUMBING_TYPE = "BBBBB";

    private static final Boolean DEFAULT_IS_CONNECTION_AVAILABLE_FROM_PROPERTY = false;
    private static final Boolean UPDATED_IS_CONNECTION_AVAILABLE_FROM_PROPERTY = true;

    private static final Boolean DEFAULT_IS_CONNECTION_READY_FOR_HOOKUP = false;
    private static final Boolean UPDATED_IS_CONNECTION_READY_FOR_HOOKUP = true;

    private static final Boolean DEFAULT_HAS_MASTER_SEWER_APPROVAL_LETTER = false;
    private static final Boolean UPDATED_HAS_MASTER_SEWER_APPROVAL_LETTER = true;
    private static final String DEFAULT_PLAT_SUBDIVISION = "AAAAA";
    private static final String UPDATED_PLAT_SUBDIVISION = "BBBBB";

    private static final Boolean DEFAULT_HAS_ASSESSMENT_OR_LIEN = false;
    private static final Boolean UPDATED_HAS_ASSESSMENT_OR_LIEN = true;

    private static final Boolean DEFAULT_IS_ASSESSMENT_OR_LIEN_PAID = false;
    private static final Boolean UPDATED_IS_ASSESSMENT_OR_LIEN_PAID = true;

    private static final WorkSiteType DEFAULT_WORK_SITE_TYPE = WorkSiteType.PUBLIC;
    private static final WorkSiteType UPDATED_WORK_SITE_TYPE = WorkSiteType.PRIVATE;
    private static final String DEFAULT_STREET_ADDRESS = "AAAAA";
    private static final String UPDATED_STREET_ADDRESS = "BBBBB";
    private static final String DEFAULT_ZIP_CODE = "AAAAA";
    private static final String UPDATED_ZIP_CODE = "BBBBB";
    private static final String DEFAULT_CITY = "AAAAA";
    private static final String UPDATED_CITY = "BBBBB";
    private static final String DEFAULT_STATE = "AAAAA";
    private static final String UPDATED_STATE = "BBBBB";

    private static final Boolean DEFAULT_INCLUDES_EXCAVATION = false;
    private static final Boolean UPDATED_INCLUDES_EXCAVATION = true;
    private static final String DEFAULT_EXCAVATION_DESCRIPTION = "AAAAA";
    private static final String UPDATED_EXCAVATION_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_EXCAVATION_PERMIT_NUMBER = "AAAAA";
    private static final String UPDATED_EXCAVATION_PERMIT_NUMBER = "BBBBB";

    private static final Boolean DEFAULT_IS_NEW_CONSTRUCTION = false;
    private static final Boolean UPDATED_IS_NEW_CONSTRUCTION = true;
    private static final String DEFAULT_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER = "AAAAA";
    private static final String UPDATED_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER = "BBBBB";
    private static final String DEFAULT_ZONING_DISTRICT = "AAAAA";
    private static final String UPDATED_ZONING_DISTRICT = "BBBBB";
    private static final String DEFAULT_OCC_GROUP = "AAAAA";
    private static final String UPDATED_OCC_GROUP = "BBBBB";
    private static final String DEFAULT_CONSTRUCTION_TYPE = "AAAAA";
    private static final String UPDATED_CONSTRUCTION_TYPE = "BBBBB";
    private static final String DEFAULT_STRUCTURAL_CLASS = "AAAAA";
    private static final String UPDATED_STRUCTURAL_CLASS = "BBBBB";

    private static final Boolean DEFAULT_HAS_SITE_PLAN = false;
    private static final Boolean UPDATED_HAS_SITE_PLAN = true;
    private static final String DEFAULT_CONTROL_NUMBER = "AAAAA";
    private static final String UPDATED_CONTROL_NUMBER = "BBBBB";

    private static final Integer DEFAULT_TOTAL_FLOORS = 1;
    private static final Integer UPDATED_TOTAL_FLOORS = 2;

    private static final BigDecimal DEFAULT_GROSS_BUILDING_AREA = new BigDecimal(1);
    private static final BigDecimal UPDATED_GROSS_BUILDING_AREA = new BigDecimal(2);

    private static final Integer DEFAULT_TOTAL_DWELLING_UNITS = 1;
    private static final Integer UPDATED_TOTAL_DWELLING_UNITS = 2;
    private static final String DEFAULT_SERVICE_LINE_LOCATION = "AAAAA";
    private static final String UPDATED_SERVICE_LINE_LOCATION = "BBBBB";
    private static final String DEFAULT_EXISTING_SEWER_MATERIAL_DESCRIPTION = "AAAAA";
    private static final String UPDATED_EXISTING_SEWER_MATERIAL_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_CONNECTION_TYPE = "AAAAA";
    private static final String UPDATED_CONNECTION_TYPE = "BBBBB";
    private static final String DEFAULT_SERVICE_LINE_SIZE = "AAAAA";
    private static final String UPDATED_SERVICE_LINE_SIZE = "BBBBB";
    private static final String DEFAULT_SEWER_MAIN_SIZE = "AAAAA";
    private static final String UPDATED_SEWER_MAIN_SIZE = "BBBBB";

    private static final Boolean DEFAULT_USES_RIGHT_OF_WAY = false;
    private static final Boolean UPDATED_USES_RIGHT_OF_WAY = true;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProjectMockMvc;

    private Project project;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProjectResource projectResource = new ProjectResource();
        ReflectionTestUtils.setField(projectResource, "projectRepository", projectRepository);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        project = new Project();
        project.setProjectName(DEFAULT_PROJECT_NAME);
        project.setProjectDescription(DEFAULT_PROJECT_DESCRIPTION);
        project.setProjectStatus(DEFAULT_PROJECT_STATUS);
        project.setAddedDate(DEFAULT_ADDED_DATE);
        project.setSubmittedDate(DEFAULT_SUBMITTED_DATE);
        project.setApprovedDate(DEFAULT_APPROVED_DATE);
        project.setIsWorkOnExistingPlumbing(DEFAULT_IS_WORK_ON_EXISTING_PLUMBING);
        project.setRepairLocation(DEFAULT_REPAIR_LOCATION);
        project.setCustomRepairLocation(DEFAULT_CUSTOM_REPAIR_LOCATION);
        project.setMeterLocation(DEFAULT_METER_LOCATION);
        project.setIsNewLine(DEFAULT_IS_NEW_LINE);
        project.setPlumbingType(DEFAULT_PLUMBING_TYPE);
        project.setCustomPlumbingType(DEFAULT_CUSTOM_PLUMBING_TYPE);
        project.setIsConnectionAvailableFromProperty(DEFAULT_IS_CONNECTION_AVAILABLE_FROM_PROPERTY);
        project.setIsConnectionReadyForHookup(DEFAULT_IS_CONNECTION_READY_FOR_HOOKUP);
        project.setHasMasterSewerApprovalLetter(DEFAULT_HAS_MASTER_SEWER_APPROVAL_LETTER);
        project.setPlatSubdivision(DEFAULT_PLAT_SUBDIVISION);
        project.setHasAssessmentOrLien(DEFAULT_HAS_ASSESSMENT_OR_LIEN);
        project.setIsAssessmentOrLienPaid(DEFAULT_IS_ASSESSMENT_OR_LIEN_PAID);
        project.setWorkSiteType(DEFAULT_WORK_SITE_TYPE);
        project.setStreetAddress(DEFAULT_STREET_ADDRESS);
        project.setZipCode(DEFAULT_ZIP_CODE);
        project.setCity(DEFAULT_CITY);
        project.setState(DEFAULT_STATE);
        project.setIncludesExcavation(DEFAULT_INCLUDES_EXCAVATION);
        project.setExcavationDescription(DEFAULT_EXCAVATION_DESCRIPTION);
        project.setExcavationPermitNumber(DEFAULT_EXCAVATION_PERMIT_NUMBER);
        project.setIsNewConstruction(DEFAULT_IS_NEW_CONSTRUCTION);
        project.setNewConstructionBuildingPermitNumber(DEFAULT_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER);
        project.setZoningDistrict(DEFAULT_ZONING_DISTRICT);
        project.setOccGroup(DEFAULT_OCC_GROUP);
        project.setConstructionType(DEFAULT_CONSTRUCTION_TYPE);
        project.setStructuralClass(DEFAULT_STRUCTURAL_CLASS);
        project.setHasSitePlan(DEFAULT_HAS_SITE_PLAN);
        project.setControlNumber(DEFAULT_CONTROL_NUMBER);
        project.setTotalFloors(DEFAULT_TOTAL_FLOORS);
        project.setGrossBuildingArea(DEFAULT_GROSS_BUILDING_AREA);
        project.setTotalDwellingUnits(DEFAULT_TOTAL_DWELLING_UNITS);
        project.setServiceLineLocation(DEFAULT_SERVICE_LINE_LOCATION);
        project.setExistingSewerMaterialDescription(DEFAULT_EXISTING_SEWER_MATERIAL_DESCRIPTION);
        project.setConnectionType(DEFAULT_CONNECTION_TYPE);
        project.setServiceLineSize(DEFAULT_SERVICE_LINE_SIZE);
        project.setSewerMainSize(DEFAULT_SEWER_MAIN_SIZE);
        project.setUsesRightOfWay(DEFAULT_USES_RIGHT_OF_WAY);
    }

    @Test
    @Transactional
    public void createProject() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project

        restProjectMockMvc.perform(post("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(project)))
                .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projects.get(projects.size() - 1);
        assertThat(testProject.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testProject.getProjectDescription()).isEqualTo(DEFAULT_PROJECT_DESCRIPTION);
        assertThat(testProject.getProjectStatus()).isEqualTo(DEFAULT_PROJECT_STATUS);
        assertThat(testProject.getAddedDate()).isEqualTo(DEFAULT_ADDED_DATE);
        assertThat(testProject.getSubmittedDate()).isEqualTo(DEFAULT_SUBMITTED_DATE);
        assertThat(testProject.getApprovedDate()).isEqualTo(DEFAULT_APPROVED_DATE);
        assertThat(testProject.isIsWorkOnExistingPlumbing()).isEqualTo(DEFAULT_IS_WORK_ON_EXISTING_PLUMBING);
        assertThat(testProject.getRepairLocation()).isEqualTo(DEFAULT_REPAIR_LOCATION);
        assertThat(testProject.getCustomRepairLocation()).isEqualTo(DEFAULT_CUSTOM_REPAIR_LOCATION);
        assertThat(testProject.getMeterLocation()).isEqualTo(DEFAULT_METER_LOCATION);
        assertThat(testProject.isIsNewLine()).isEqualTo(DEFAULT_IS_NEW_LINE);
        assertThat(testProject.getPlumbingType()).isEqualTo(DEFAULT_PLUMBING_TYPE);
        assertThat(testProject.getCustomPlumbingType()).isEqualTo(DEFAULT_CUSTOM_PLUMBING_TYPE);
        assertThat(testProject.isIsConnectionAvailableFromProperty()).isEqualTo(DEFAULT_IS_CONNECTION_AVAILABLE_FROM_PROPERTY);
        assertThat(testProject.isIsConnectionReadyForHookup()).isEqualTo(DEFAULT_IS_CONNECTION_READY_FOR_HOOKUP);
        assertThat(testProject.isHasMasterSewerApprovalLetter()).isEqualTo(DEFAULT_HAS_MASTER_SEWER_APPROVAL_LETTER);
        assertThat(testProject.getPlatSubdivision()).isEqualTo(DEFAULT_PLAT_SUBDIVISION);
        assertThat(testProject.isHasAssessmentOrLien()).isEqualTo(DEFAULT_HAS_ASSESSMENT_OR_LIEN);
        assertThat(testProject.isIsAssessmentOrLienPaid()).isEqualTo(DEFAULT_IS_ASSESSMENT_OR_LIEN_PAID);
        assertThat(testProject.getWorkSiteType()).isEqualTo(DEFAULT_WORK_SITE_TYPE);
        assertThat(testProject.getStreetAddress()).isEqualTo(DEFAULT_STREET_ADDRESS);
        assertThat(testProject.getZipCode()).isEqualTo(DEFAULT_ZIP_CODE);
        assertThat(testProject.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testProject.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testProject.isIncludesExcavation()).isEqualTo(DEFAULT_INCLUDES_EXCAVATION);
        assertThat(testProject.getExcavationDescription()).isEqualTo(DEFAULT_EXCAVATION_DESCRIPTION);
        assertThat(testProject.getExcavationPermitNumber()).isEqualTo(DEFAULT_EXCAVATION_PERMIT_NUMBER);
        assertThat(testProject.isIsNewConstruction()).isEqualTo(DEFAULT_IS_NEW_CONSTRUCTION);
        assertThat(testProject.getNewConstructionBuildingPermitNumber()).isEqualTo(DEFAULT_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER);
        assertThat(testProject.getZoningDistrict()).isEqualTo(DEFAULT_ZONING_DISTRICT);
        assertThat(testProject.getOccGroup()).isEqualTo(DEFAULT_OCC_GROUP);
        assertThat(testProject.getConstructionType()).isEqualTo(DEFAULT_CONSTRUCTION_TYPE);
        assertThat(testProject.getStructuralClass()).isEqualTo(DEFAULT_STRUCTURAL_CLASS);
        assertThat(testProject.isHasSitePlan()).isEqualTo(DEFAULT_HAS_SITE_PLAN);
        assertThat(testProject.getControlNumber()).isEqualTo(DEFAULT_CONTROL_NUMBER);
        assertThat(testProject.getTotalFloors()).isEqualTo(DEFAULT_TOTAL_FLOORS);
        assertThat(testProject.getGrossBuildingArea()).isEqualTo(DEFAULT_GROSS_BUILDING_AREA);
        assertThat(testProject.getTotalDwellingUnits()).isEqualTo(DEFAULT_TOTAL_DWELLING_UNITS);
        assertThat(testProject.getServiceLineLocation()).isEqualTo(DEFAULT_SERVICE_LINE_LOCATION);
        assertThat(testProject.getExistingSewerMaterialDescription()).isEqualTo(DEFAULT_EXISTING_SEWER_MATERIAL_DESCRIPTION);
        assertThat(testProject.getConnectionType()).isEqualTo(DEFAULT_CONNECTION_TYPE);
        assertThat(testProject.getServiceLineSize()).isEqualTo(DEFAULT_SERVICE_LINE_SIZE);
        assertThat(testProject.getSewerMainSize()).isEqualTo(DEFAULT_SEWER_MAIN_SIZE);
        assertThat(testProject.isUsesRightOfWay()).isEqualTo(DEFAULT_USES_RIGHT_OF_WAY);
    }

    @Test
    @Transactional
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projects
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
                .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME.toString())))
                .andExpect(jsonPath("$.[*].projectDescription").value(hasItem(DEFAULT_PROJECT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].projectStatus").value(hasItem(DEFAULT_PROJECT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].addedDate").value(hasItem(DEFAULT_ADDED_DATE.toString())))
                .andExpect(jsonPath("$.[*].submittedDate").value(hasItem(DEFAULT_SUBMITTED_DATE.toString())))
                .andExpect(jsonPath("$.[*].approvedDate").value(hasItem(DEFAULT_APPROVED_DATE.toString())))
                .andExpect(jsonPath("$.[*].isWorkOnExistingPlumbing").value(hasItem(DEFAULT_IS_WORK_ON_EXISTING_PLUMBING.booleanValue())))
                .andExpect(jsonPath("$.[*].repairLocation").value(hasItem(DEFAULT_REPAIR_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].customRepairLocation").value(hasItem(DEFAULT_CUSTOM_REPAIR_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].meterLocation").value(hasItem(DEFAULT_METER_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].isNewLine").value(hasItem(DEFAULT_IS_NEW_LINE.booleanValue())))
                .andExpect(jsonPath("$.[*].plumbingType").value(hasItem(DEFAULT_PLUMBING_TYPE.toString())))
                .andExpect(jsonPath("$.[*].customPlumbingType").value(hasItem(DEFAULT_CUSTOM_PLUMBING_TYPE.toString())))
                .andExpect(jsonPath("$.[*].isConnectionAvailableFromProperty").value(hasItem(DEFAULT_IS_CONNECTION_AVAILABLE_FROM_PROPERTY.booleanValue())))
                .andExpect(jsonPath("$.[*].isConnectionReadyForHookup").value(hasItem(DEFAULT_IS_CONNECTION_READY_FOR_HOOKUP.booleanValue())))
                .andExpect(jsonPath("$.[*].hasMasterSewerApprovalLetter").value(hasItem(DEFAULT_HAS_MASTER_SEWER_APPROVAL_LETTER.booleanValue())))
                .andExpect(jsonPath("$.[*].platSubdivision").value(hasItem(DEFAULT_PLAT_SUBDIVISION.toString())))
                .andExpect(jsonPath("$.[*].hasAssessmentOrLien").value(hasItem(DEFAULT_HAS_ASSESSMENT_OR_LIEN.booleanValue())))
                .andExpect(jsonPath("$.[*].isAssessmentOrLienPaid").value(hasItem(DEFAULT_IS_ASSESSMENT_OR_LIEN_PAID.booleanValue())))
                .andExpect(jsonPath("$.[*].workSiteType").value(hasItem(DEFAULT_WORK_SITE_TYPE.toString())))
                .andExpect(jsonPath("$.[*].streetAddress").value(hasItem(DEFAULT_STREET_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].zipCode").value(hasItem(DEFAULT_ZIP_CODE.toString())))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
                .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
                .andExpect(jsonPath("$.[*].includesExcavation").value(hasItem(DEFAULT_INCLUDES_EXCAVATION.booleanValue())))
                .andExpect(jsonPath("$.[*].excavationDescription").value(hasItem(DEFAULT_EXCAVATION_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].excavationPermitNumber").value(hasItem(DEFAULT_EXCAVATION_PERMIT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].isNewConstruction").value(hasItem(DEFAULT_IS_NEW_CONSTRUCTION.booleanValue())))
                .andExpect(jsonPath("$.[*].newConstructionBuildingPermitNumber").value(hasItem(DEFAULT_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].zoningDistrict").value(hasItem(DEFAULT_ZONING_DISTRICT.toString())))
                .andExpect(jsonPath("$.[*].occGroup").value(hasItem(DEFAULT_OCC_GROUP.toString())))
                .andExpect(jsonPath("$.[*].constructionType").value(hasItem(DEFAULT_CONSTRUCTION_TYPE.toString())))
                .andExpect(jsonPath("$.[*].structuralClass").value(hasItem(DEFAULT_STRUCTURAL_CLASS.toString())))
                .andExpect(jsonPath("$.[*].hasSitePlan").value(hasItem(DEFAULT_HAS_SITE_PLAN.booleanValue())))
                .andExpect(jsonPath("$.[*].controlNumber").value(hasItem(DEFAULT_CONTROL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].totalFloors").value(hasItem(DEFAULT_TOTAL_FLOORS)))
                .andExpect(jsonPath("$.[*].grossBuildingArea").value(hasItem(DEFAULT_GROSS_BUILDING_AREA.intValue())))
                .andExpect(jsonPath("$.[*].totalDwellingUnits").value(hasItem(DEFAULT_TOTAL_DWELLING_UNITS)))
                .andExpect(jsonPath("$.[*].serviceLineLocation").value(hasItem(DEFAULT_SERVICE_LINE_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].existingSewerMaterialDescription").value(hasItem(DEFAULT_EXISTING_SEWER_MATERIAL_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].connectionType").value(hasItem(DEFAULT_CONNECTION_TYPE.toString())))
                .andExpect(jsonPath("$.[*].serviceLineSize").value(hasItem(DEFAULT_SERVICE_LINE_SIZE.toString())))
                .andExpect(jsonPath("$.[*].sewerMainSize").value(hasItem(DEFAULT_SEWER_MAIN_SIZE.toString())))
                .andExpect(jsonPath("$.[*].usesRightOfWay").value(hasItem(DEFAULT_USES_RIGHT_OF_WAY.booleanValue())));
    }

    @Test
    @Transactional
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME.toString()))
            .andExpect(jsonPath("$.projectDescription").value(DEFAULT_PROJECT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.projectStatus").value(DEFAULT_PROJECT_STATUS.toString()))
            .andExpect(jsonPath("$.addedDate").value(DEFAULT_ADDED_DATE.toString()))
            .andExpect(jsonPath("$.submittedDate").value(DEFAULT_SUBMITTED_DATE.toString()))
            .andExpect(jsonPath("$.approvedDate").value(DEFAULT_APPROVED_DATE.toString()))
            .andExpect(jsonPath("$.isWorkOnExistingPlumbing").value(DEFAULT_IS_WORK_ON_EXISTING_PLUMBING.booleanValue()))
            .andExpect(jsonPath("$.repairLocation").value(DEFAULT_REPAIR_LOCATION.toString()))
            .andExpect(jsonPath("$.customRepairLocation").value(DEFAULT_CUSTOM_REPAIR_LOCATION.toString()))
            .andExpect(jsonPath("$.meterLocation").value(DEFAULT_METER_LOCATION.toString()))
            .andExpect(jsonPath("$.isNewLine").value(DEFAULT_IS_NEW_LINE.booleanValue()))
            .andExpect(jsonPath("$.plumbingType").value(DEFAULT_PLUMBING_TYPE.toString()))
            .andExpect(jsonPath("$.customPlumbingType").value(DEFAULT_CUSTOM_PLUMBING_TYPE.toString()))
            .andExpect(jsonPath("$.isConnectionAvailableFromProperty").value(DEFAULT_IS_CONNECTION_AVAILABLE_FROM_PROPERTY.booleanValue()))
            .andExpect(jsonPath("$.isConnectionReadyForHookup").value(DEFAULT_IS_CONNECTION_READY_FOR_HOOKUP.booleanValue()))
            .andExpect(jsonPath("$.hasMasterSewerApprovalLetter").value(DEFAULT_HAS_MASTER_SEWER_APPROVAL_LETTER.booleanValue()))
            .andExpect(jsonPath("$.platSubdivision").value(DEFAULT_PLAT_SUBDIVISION.toString()))
            .andExpect(jsonPath("$.hasAssessmentOrLien").value(DEFAULT_HAS_ASSESSMENT_OR_LIEN.booleanValue()))
            .andExpect(jsonPath("$.isAssessmentOrLienPaid").value(DEFAULT_IS_ASSESSMENT_OR_LIEN_PAID.booleanValue()))
            .andExpect(jsonPath("$.workSiteType").value(DEFAULT_WORK_SITE_TYPE.toString()))
            .andExpect(jsonPath("$.streetAddress").value(DEFAULT_STREET_ADDRESS.toString()))
            .andExpect(jsonPath("$.zipCode").value(DEFAULT_ZIP_CODE.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.includesExcavation").value(DEFAULT_INCLUDES_EXCAVATION.booleanValue()))
            .andExpect(jsonPath("$.excavationDescription").value(DEFAULT_EXCAVATION_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.excavationPermitNumber").value(DEFAULT_EXCAVATION_PERMIT_NUMBER.toString()))
            .andExpect(jsonPath("$.isNewConstruction").value(DEFAULT_IS_NEW_CONSTRUCTION.booleanValue()))
            .andExpect(jsonPath("$.newConstructionBuildingPermitNumber").value(DEFAULT_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER.toString()))
            .andExpect(jsonPath("$.zoningDistrict").value(DEFAULT_ZONING_DISTRICT.toString()))
            .andExpect(jsonPath("$.occGroup").value(DEFAULT_OCC_GROUP.toString()))
            .andExpect(jsonPath("$.constructionType").value(DEFAULT_CONSTRUCTION_TYPE.toString()))
            .andExpect(jsonPath("$.structuralClass").value(DEFAULT_STRUCTURAL_CLASS.toString()))
            .andExpect(jsonPath("$.hasSitePlan").value(DEFAULT_HAS_SITE_PLAN.booleanValue()))
            .andExpect(jsonPath("$.controlNumber").value(DEFAULT_CONTROL_NUMBER.toString()))
            .andExpect(jsonPath("$.totalFloors").value(DEFAULT_TOTAL_FLOORS))
            .andExpect(jsonPath("$.grossBuildingArea").value(DEFAULT_GROSS_BUILDING_AREA.intValue()))
            .andExpect(jsonPath("$.totalDwellingUnits").value(DEFAULT_TOTAL_DWELLING_UNITS))
            .andExpect(jsonPath("$.serviceLineLocation").value(DEFAULT_SERVICE_LINE_LOCATION.toString()))
            .andExpect(jsonPath("$.existingSewerMaterialDescription").value(DEFAULT_EXISTING_SEWER_MATERIAL_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.connectionType").value(DEFAULT_CONNECTION_TYPE.toString()))
            .andExpect(jsonPath("$.serviceLineSize").value(DEFAULT_SERVICE_LINE_SIZE.toString()))
            .andExpect(jsonPath("$.sewerMainSize").value(DEFAULT_SEWER_MAIN_SIZE.toString()))
            .andExpect(jsonPath("$.usesRightOfWay").value(DEFAULT_USES_RIGHT_OF_WAY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Update the project
        Project updatedProject = new Project();
        updatedProject.setId(project.getId());
        updatedProject.setProjectName(UPDATED_PROJECT_NAME);
        updatedProject.setProjectDescription(UPDATED_PROJECT_DESCRIPTION);
        updatedProject.setProjectStatus(UPDATED_PROJECT_STATUS);
        updatedProject.setAddedDate(UPDATED_ADDED_DATE);
        updatedProject.setSubmittedDate(UPDATED_SUBMITTED_DATE);
        updatedProject.setApprovedDate(UPDATED_APPROVED_DATE);
        updatedProject.setIsWorkOnExistingPlumbing(UPDATED_IS_WORK_ON_EXISTING_PLUMBING);
        updatedProject.setRepairLocation(UPDATED_REPAIR_LOCATION);
        updatedProject.setCustomRepairLocation(UPDATED_CUSTOM_REPAIR_LOCATION);
        updatedProject.setMeterLocation(UPDATED_METER_LOCATION);
        updatedProject.setIsNewLine(UPDATED_IS_NEW_LINE);
        updatedProject.setPlumbingType(UPDATED_PLUMBING_TYPE);
        updatedProject.setCustomPlumbingType(UPDATED_CUSTOM_PLUMBING_TYPE);
        updatedProject.setIsConnectionAvailableFromProperty(UPDATED_IS_CONNECTION_AVAILABLE_FROM_PROPERTY);
        updatedProject.setIsConnectionReadyForHookup(UPDATED_IS_CONNECTION_READY_FOR_HOOKUP);
        updatedProject.setHasMasterSewerApprovalLetter(UPDATED_HAS_MASTER_SEWER_APPROVAL_LETTER);
        updatedProject.setPlatSubdivision(UPDATED_PLAT_SUBDIVISION);
        updatedProject.setHasAssessmentOrLien(UPDATED_HAS_ASSESSMENT_OR_LIEN);
        updatedProject.setIsAssessmentOrLienPaid(UPDATED_IS_ASSESSMENT_OR_LIEN_PAID);
        updatedProject.setWorkSiteType(UPDATED_WORK_SITE_TYPE);
        updatedProject.setStreetAddress(UPDATED_STREET_ADDRESS);
        updatedProject.setZipCode(UPDATED_ZIP_CODE);
        updatedProject.setCity(UPDATED_CITY);
        updatedProject.setState(UPDATED_STATE);
        updatedProject.setIncludesExcavation(UPDATED_INCLUDES_EXCAVATION);
        updatedProject.setExcavationDescription(UPDATED_EXCAVATION_DESCRIPTION);
        updatedProject.setExcavationPermitNumber(UPDATED_EXCAVATION_PERMIT_NUMBER);
        updatedProject.setIsNewConstruction(UPDATED_IS_NEW_CONSTRUCTION);
        updatedProject.setNewConstructionBuildingPermitNumber(UPDATED_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER);
        updatedProject.setZoningDistrict(UPDATED_ZONING_DISTRICT);
        updatedProject.setOccGroup(UPDATED_OCC_GROUP);
        updatedProject.setConstructionType(UPDATED_CONSTRUCTION_TYPE);
        updatedProject.setStructuralClass(UPDATED_STRUCTURAL_CLASS);
        updatedProject.setHasSitePlan(UPDATED_HAS_SITE_PLAN);
        updatedProject.setControlNumber(UPDATED_CONTROL_NUMBER);
        updatedProject.setTotalFloors(UPDATED_TOTAL_FLOORS);
        updatedProject.setGrossBuildingArea(UPDATED_GROSS_BUILDING_AREA);
        updatedProject.setTotalDwellingUnits(UPDATED_TOTAL_DWELLING_UNITS);
        updatedProject.setServiceLineLocation(UPDATED_SERVICE_LINE_LOCATION);
        updatedProject.setExistingSewerMaterialDescription(UPDATED_EXISTING_SEWER_MATERIAL_DESCRIPTION);
        updatedProject.setConnectionType(UPDATED_CONNECTION_TYPE);
        updatedProject.setServiceLineSize(UPDATED_SERVICE_LINE_SIZE);
        updatedProject.setSewerMainSize(UPDATED_SEWER_MAIN_SIZE);
        updatedProject.setUsesRightOfWay(UPDATED_USES_RIGHT_OF_WAY);

        restProjectMockMvc.perform(put("/api/projects")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedProject)))
                .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(databaseSizeBeforeUpdate);
        Project testProject = projects.get(projects.size() - 1);
        assertThat(testProject.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testProject.getProjectDescription()).isEqualTo(UPDATED_PROJECT_DESCRIPTION);
        assertThat(testProject.getProjectStatus()).isEqualTo(UPDATED_PROJECT_STATUS);
        assertThat(testProject.getAddedDate()).isEqualTo(UPDATED_ADDED_DATE);
        assertThat(testProject.getSubmittedDate()).isEqualTo(UPDATED_SUBMITTED_DATE);
        assertThat(testProject.getApprovedDate()).isEqualTo(UPDATED_APPROVED_DATE);
        assertThat(testProject.isIsWorkOnExistingPlumbing()).isEqualTo(UPDATED_IS_WORK_ON_EXISTING_PLUMBING);
        assertThat(testProject.getRepairLocation()).isEqualTo(UPDATED_REPAIR_LOCATION);
        assertThat(testProject.getCustomRepairLocation()).isEqualTo(UPDATED_CUSTOM_REPAIR_LOCATION);
        assertThat(testProject.getMeterLocation()).isEqualTo(UPDATED_METER_LOCATION);
        assertThat(testProject.isIsNewLine()).isEqualTo(UPDATED_IS_NEW_LINE);
        assertThat(testProject.getPlumbingType()).isEqualTo(UPDATED_PLUMBING_TYPE);
        assertThat(testProject.getCustomPlumbingType()).isEqualTo(UPDATED_CUSTOM_PLUMBING_TYPE);
        assertThat(testProject.isIsConnectionAvailableFromProperty()).isEqualTo(UPDATED_IS_CONNECTION_AVAILABLE_FROM_PROPERTY);
        assertThat(testProject.isIsConnectionReadyForHookup()).isEqualTo(UPDATED_IS_CONNECTION_READY_FOR_HOOKUP);
        assertThat(testProject.isHasMasterSewerApprovalLetter()).isEqualTo(UPDATED_HAS_MASTER_SEWER_APPROVAL_LETTER);
        assertThat(testProject.getPlatSubdivision()).isEqualTo(UPDATED_PLAT_SUBDIVISION);
        assertThat(testProject.isHasAssessmentOrLien()).isEqualTo(UPDATED_HAS_ASSESSMENT_OR_LIEN);
        assertThat(testProject.isIsAssessmentOrLienPaid()).isEqualTo(UPDATED_IS_ASSESSMENT_OR_LIEN_PAID);
        assertThat(testProject.getWorkSiteType()).isEqualTo(UPDATED_WORK_SITE_TYPE);
        assertThat(testProject.getStreetAddress()).isEqualTo(UPDATED_STREET_ADDRESS);
        assertThat(testProject.getZipCode()).isEqualTo(UPDATED_ZIP_CODE);
        assertThat(testProject.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testProject.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testProject.isIncludesExcavation()).isEqualTo(UPDATED_INCLUDES_EXCAVATION);
        assertThat(testProject.getExcavationDescription()).isEqualTo(UPDATED_EXCAVATION_DESCRIPTION);
        assertThat(testProject.getExcavationPermitNumber()).isEqualTo(UPDATED_EXCAVATION_PERMIT_NUMBER);
        assertThat(testProject.isIsNewConstruction()).isEqualTo(UPDATED_IS_NEW_CONSTRUCTION);
        assertThat(testProject.getNewConstructionBuildingPermitNumber()).isEqualTo(UPDATED_NEW_CONSTRUCTION_BUILDING_PERMIT_NUMBER);
        assertThat(testProject.getZoningDistrict()).isEqualTo(UPDATED_ZONING_DISTRICT);
        assertThat(testProject.getOccGroup()).isEqualTo(UPDATED_OCC_GROUP);
        assertThat(testProject.getConstructionType()).isEqualTo(UPDATED_CONSTRUCTION_TYPE);
        assertThat(testProject.getStructuralClass()).isEqualTo(UPDATED_STRUCTURAL_CLASS);
        assertThat(testProject.isHasSitePlan()).isEqualTo(UPDATED_HAS_SITE_PLAN);
        assertThat(testProject.getControlNumber()).isEqualTo(UPDATED_CONTROL_NUMBER);
        assertThat(testProject.getTotalFloors()).isEqualTo(UPDATED_TOTAL_FLOORS);
        assertThat(testProject.getGrossBuildingArea()).isEqualTo(UPDATED_GROSS_BUILDING_AREA);
        assertThat(testProject.getTotalDwellingUnits()).isEqualTo(UPDATED_TOTAL_DWELLING_UNITS);
        assertThat(testProject.getServiceLineLocation()).isEqualTo(UPDATED_SERVICE_LINE_LOCATION);
        assertThat(testProject.getExistingSewerMaterialDescription()).isEqualTo(UPDATED_EXISTING_SEWER_MATERIAL_DESCRIPTION);
        assertThat(testProject.getConnectionType()).isEqualTo(UPDATED_CONNECTION_TYPE);
        assertThat(testProject.getServiceLineSize()).isEqualTo(UPDATED_SERVICE_LINE_SIZE);
        assertThat(testProject.getSewerMainSize()).isEqualTo(UPDATED_SEWER_MAIN_SIZE);
        assertThat(testProject.isUsesRightOfWay()).isEqualTo(UPDATED_USES_RIGHT_OF_WAY);
    }

    @Test
    @Transactional
    public void deleteProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);
        int databaseSizeBeforeDelete = projectRepository.findAll().size();

        // Get the project
        restProjectMockMvc.perform(delete("/api/projects/{id}", project.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Project> projects = projectRepository.findAll();
        assertThat(projects).hasSize(databaseSizeBeforeDelete - 1);
    }
}
