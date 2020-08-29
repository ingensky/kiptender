package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.MeasurementParameter;
import com.ingen.sky.repository.MeasurementParameterRepository;
import com.ingen.sky.service.MeasurementParameterService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MeasurementParameterResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class MeasurementParameterResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private MeasurementParameterRepository measurementParameterRepository;

    @Autowired
    private MeasurementParameterService measurementParameterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMeasurementParameterMockMvc;

    private MeasurementParameter measurementParameter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeasurementParameter createEntity(EntityManager em) {
        MeasurementParameter measurementParameter = new MeasurementParameter()
            .title(DEFAULT_TITLE);
        return measurementParameter;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MeasurementParameter createUpdatedEntity(EntityManager em) {
        MeasurementParameter measurementParameter = new MeasurementParameter()
            .title(UPDATED_TITLE);
        return measurementParameter;
    }

    @BeforeEach
    public void initTest() {
        measurementParameter = createEntity(em);
    }

    @Test
    @Transactional
    public void createMeasurementParameter() throws Exception {
        int databaseSizeBeforeCreate = measurementParameterRepository.findAll().size();
        // Create the MeasurementParameter
        restMeasurementParameterMockMvc.perform(post("/api/measurement-parameters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measurementParameter)))
            .andExpect(status().isCreated());

        // Validate the MeasurementParameter in the database
        List<MeasurementParameter> measurementParameterList = measurementParameterRepository.findAll();
        assertThat(measurementParameterList).hasSize(databaseSizeBeforeCreate + 1);
        MeasurementParameter testMeasurementParameter = measurementParameterList.get(measurementParameterList.size() - 1);
        assertThat(testMeasurementParameter.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createMeasurementParameterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = measurementParameterRepository.findAll().size();

        // Create the MeasurementParameter with an existing ID
        measurementParameter.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMeasurementParameterMockMvc.perform(post("/api/measurement-parameters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measurementParameter)))
            .andExpect(status().isBadRequest());

        // Validate the MeasurementParameter in the database
        List<MeasurementParameter> measurementParameterList = measurementParameterRepository.findAll();
        assertThat(measurementParameterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMeasurementParameters() throws Exception {
        // Initialize the database
        measurementParameterRepository.saveAndFlush(measurementParameter);

        // Get all the measurementParameterList
        restMeasurementParameterMockMvc.perform(get("/api/measurement-parameters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(measurementParameter.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }
    
    @Test
    @Transactional
    public void getMeasurementParameter() throws Exception {
        // Initialize the database
        measurementParameterRepository.saveAndFlush(measurementParameter);

        // Get the measurementParameter
        restMeasurementParameterMockMvc.perform(get("/api/measurement-parameters/{id}", measurementParameter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(measurementParameter.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }
    @Test
    @Transactional
    public void getNonExistingMeasurementParameter() throws Exception {
        // Get the measurementParameter
        restMeasurementParameterMockMvc.perform(get("/api/measurement-parameters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeasurementParameter() throws Exception {
        // Initialize the database
        measurementParameterService.save(measurementParameter);

        int databaseSizeBeforeUpdate = measurementParameterRepository.findAll().size();

        // Update the measurementParameter
        MeasurementParameter updatedMeasurementParameter = measurementParameterRepository.findById(measurementParameter.getId()).get();
        // Disconnect from session so that the updates on updatedMeasurementParameter are not directly saved in db
        em.detach(updatedMeasurementParameter);
        updatedMeasurementParameter
            .title(UPDATED_TITLE);

        restMeasurementParameterMockMvc.perform(put("/api/measurement-parameters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedMeasurementParameter)))
            .andExpect(status().isOk());

        // Validate the MeasurementParameter in the database
        List<MeasurementParameter> measurementParameterList = measurementParameterRepository.findAll();
        assertThat(measurementParameterList).hasSize(databaseSizeBeforeUpdate);
        MeasurementParameter testMeasurementParameter = measurementParameterList.get(measurementParameterList.size() - 1);
        assertThat(testMeasurementParameter.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingMeasurementParameter() throws Exception {
        int databaseSizeBeforeUpdate = measurementParameterRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMeasurementParameterMockMvc.perform(put("/api/measurement-parameters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(measurementParameter)))
            .andExpect(status().isBadRequest());

        // Validate the MeasurementParameter in the database
        List<MeasurementParameter> measurementParameterList = measurementParameterRepository.findAll();
        assertThat(measurementParameterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMeasurementParameter() throws Exception {
        // Initialize the database
        measurementParameterService.save(measurementParameter);

        int databaseSizeBeforeDelete = measurementParameterRepository.findAll().size();

        // Delete the measurementParameter
        restMeasurementParameterMockMvc.perform(delete("/api/measurement-parameters/{id}", measurementParameter.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MeasurementParameter> measurementParameterList = measurementParameterRepository.findAll();
        assertThat(measurementParameterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
