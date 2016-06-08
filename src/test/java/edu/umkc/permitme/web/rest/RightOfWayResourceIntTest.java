package edu.umkc.permitme.web.rest;

import edu.umkc.permitme.PermitmeApp;
import edu.umkc.permitme.domain.RightOfWay;
import edu.umkc.permitme.repository.RightOfWayRepository;

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

import edu.umkc.permitme.domain.enumeration.RightOfWayType;

/**
 * Test class for the RightOfWayResource REST controller.
 *
 * @see RightOfWayResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PermitmeApp.class)
@WebAppConfiguration
@IntegrationTest
public class RightOfWayResourceIntTest {


    private static final RightOfWayType DEFAULT_RIGHT_OF_WAY_TYPE = RightOfWayType.STREET;
    private static final RightOfWayType UPDATED_RIGHT_OF_WAY_TYPE = RightOfWayType.SIDEWALK;
    private static final String DEFAULT_CLOSURE_BOUNDARIES = "AAAAA";
    private static final String UPDATED_CLOSURE_BOUNDARIES = "BBBBB";
    private static final String DEFAULT_PROPOSED_DETOUR = "AAAAA";
    private static final String UPDATED_PROPOSED_DETOUR = "BBBBB";
    private static final String DEFAULT_RIGHT_OF_WAY_NAME = "AAAAA";
    private static final String UPDATED_RIGHT_OF_WAY_NAME = "BBBBB";

    private static final Integer DEFAULT_CLOSURE_DURATION_DAYS = 1;
    private static final Integer UPDATED_CLOSURE_DURATION_DAYS = 2;

    @Inject
    private RightOfWayRepository rightOfWayRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRightOfWayMockMvc;

    private RightOfWay rightOfWay;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RightOfWayResource rightOfWayResource = new RightOfWayResource();
        ReflectionTestUtils.setField(rightOfWayResource, "rightOfWayRepository", rightOfWayRepository);
        this.restRightOfWayMockMvc = MockMvcBuilders.standaloneSetup(rightOfWayResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        rightOfWay = new RightOfWay();
        rightOfWay.setRightOfWayType(DEFAULT_RIGHT_OF_WAY_TYPE);
        rightOfWay.setClosureBoundaries(DEFAULT_CLOSURE_BOUNDARIES);
        rightOfWay.setProposedDetour(DEFAULT_PROPOSED_DETOUR);
        rightOfWay.setRightOfWayName(DEFAULT_RIGHT_OF_WAY_NAME);
        rightOfWay.setClosureDurationDays(DEFAULT_CLOSURE_DURATION_DAYS);
    }

    @Test
    @Transactional
    public void createRightOfWay() throws Exception {
        int databaseSizeBeforeCreate = rightOfWayRepository.findAll().size();

        // Create the RightOfWay

        restRightOfWayMockMvc.perform(post("/api/right-of-ways")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(rightOfWay)))
                .andExpect(status().isCreated());

        // Validate the RightOfWay in the database
        List<RightOfWay> rightOfWays = rightOfWayRepository.findAll();
        assertThat(rightOfWays).hasSize(databaseSizeBeforeCreate + 1);
        RightOfWay testRightOfWay = rightOfWays.get(rightOfWays.size() - 1);
        assertThat(testRightOfWay.getRightOfWayType()).isEqualTo(DEFAULT_RIGHT_OF_WAY_TYPE);
        assertThat(testRightOfWay.getClosureBoundaries()).isEqualTo(DEFAULT_CLOSURE_BOUNDARIES);
        assertThat(testRightOfWay.getProposedDetour()).isEqualTo(DEFAULT_PROPOSED_DETOUR);
        assertThat(testRightOfWay.getRightOfWayName()).isEqualTo(DEFAULT_RIGHT_OF_WAY_NAME);
        assertThat(testRightOfWay.getClosureDurationDays()).isEqualTo(DEFAULT_CLOSURE_DURATION_DAYS);
    }

    @Test
    @Transactional
    public void getAllRightOfWays() throws Exception {
        // Initialize the database
        rightOfWayRepository.saveAndFlush(rightOfWay);

        // Get all the rightOfWays
        restRightOfWayMockMvc.perform(get("/api/right-of-ways?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(rightOfWay.getId().intValue())))
                .andExpect(jsonPath("$.[*].rightOfWayType").value(hasItem(DEFAULT_RIGHT_OF_WAY_TYPE.toString())))
                .andExpect(jsonPath("$.[*].closureBoundaries").value(hasItem(DEFAULT_CLOSURE_BOUNDARIES.toString())))
                .andExpect(jsonPath("$.[*].proposedDetour").value(hasItem(DEFAULT_PROPOSED_DETOUR.toString())))
                .andExpect(jsonPath("$.[*].rightOfWayName").value(hasItem(DEFAULT_RIGHT_OF_WAY_NAME.toString())))
                .andExpect(jsonPath("$.[*].closureDurationDays").value(hasItem(DEFAULT_CLOSURE_DURATION_DAYS)));
    }

    @Test
    @Transactional
    public void getRightOfWay() throws Exception {
        // Initialize the database
        rightOfWayRepository.saveAndFlush(rightOfWay);

        // Get the rightOfWay
        restRightOfWayMockMvc.perform(get("/api/right-of-ways/{id}", rightOfWay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(rightOfWay.getId().intValue()))
            .andExpect(jsonPath("$.rightOfWayType").value(DEFAULT_RIGHT_OF_WAY_TYPE.toString()))
            .andExpect(jsonPath("$.closureBoundaries").value(DEFAULT_CLOSURE_BOUNDARIES.toString()))
            .andExpect(jsonPath("$.proposedDetour").value(DEFAULT_PROPOSED_DETOUR.toString()))
            .andExpect(jsonPath("$.rightOfWayName").value(DEFAULT_RIGHT_OF_WAY_NAME.toString()))
            .andExpect(jsonPath("$.closureDurationDays").value(DEFAULT_CLOSURE_DURATION_DAYS));
    }

    @Test
    @Transactional
    public void getNonExistingRightOfWay() throws Exception {
        // Get the rightOfWay
        restRightOfWayMockMvc.perform(get("/api/right-of-ways/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRightOfWay() throws Exception {
        // Initialize the database
        rightOfWayRepository.saveAndFlush(rightOfWay);
        int databaseSizeBeforeUpdate = rightOfWayRepository.findAll().size();

        // Update the rightOfWay
        RightOfWay updatedRightOfWay = new RightOfWay();
        updatedRightOfWay.setId(rightOfWay.getId());
        updatedRightOfWay.setRightOfWayType(UPDATED_RIGHT_OF_WAY_TYPE);
        updatedRightOfWay.setClosureBoundaries(UPDATED_CLOSURE_BOUNDARIES);
        updatedRightOfWay.setProposedDetour(UPDATED_PROPOSED_DETOUR);
        updatedRightOfWay.setRightOfWayName(UPDATED_RIGHT_OF_WAY_NAME);
        updatedRightOfWay.setClosureDurationDays(UPDATED_CLOSURE_DURATION_DAYS);

        restRightOfWayMockMvc.perform(put("/api/right-of-ways")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(updatedRightOfWay)))
                .andExpect(status().isOk());

        // Validate the RightOfWay in the database
        List<RightOfWay> rightOfWays = rightOfWayRepository.findAll();
        assertThat(rightOfWays).hasSize(databaseSizeBeforeUpdate);
        RightOfWay testRightOfWay = rightOfWays.get(rightOfWays.size() - 1);
        assertThat(testRightOfWay.getRightOfWayType()).isEqualTo(UPDATED_RIGHT_OF_WAY_TYPE);
        assertThat(testRightOfWay.getClosureBoundaries()).isEqualTo(UPDATED_CLOSURE_BOUNDARIES);
        assertThat(testRightOfWay.getProposedDetour()).isEqualTo(UPDATED_PROPOSED_DETOUR);
        assertThat(testRightOfWay.getRightOfWayName()).isEqualTo(UPDATED_RIGHT_OF_WAY_NAME);
        assertThat(testRightOfWay.getClosureDurationDays()).isEqualTo(UPDATED_CLOSURE_DURATION_DAYS);
    }

    @Test
    @Transactional
    public void deleteRightOfWay() throws Exception {
        // Initialize the database
        rightOfWayRepository.saveAndFlush(rightOfWay);
        int databaseSizeBeforeDelete = rightOfWayRepository.findAll().size();

        // Get the rightOfWay
        restRightOfWayMockMvc.perform(delete("/api/right-of-ways/{id}", rightOfWay.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RightOfWay> rightOfWays = rightOfWayRepository.findAll();
        assertThat(rightOfWays).hasSize(databaseSizeBeforeDelete - 1);
    }
}
