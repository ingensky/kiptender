package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.KIPWiringDiagramType;
import com.ingen.sky.repository.KIPWiringDiagramTypeRepository;
import com.ingen.sky.service.KIPWiringDiagramTypeService;

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
 * Integration tests for the {@link KIPWiringDiagramTypeResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class KIPWiringDiagramTypeResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private KIPWiringDiagramTypeRepository kIPWiringDiagramTypeRepository;

    @Autowired
    private KIPWiringDiagramTypeService kIPWiringDiagramTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restKIPWiringDiagramTypeMockMvc;

    private KIPWiringDiagramType kIPWiringDiagramType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KIPWiringDiagramType createEntity(EntityManager em) {
        KIPWiringDiagramType kIPWiringDiagramType = new KIPWiringDiagramType()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION);
        return kIPWiringDiagramType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static KIPWiringDiagramType createUpdatedEntity(EntityManager em) {
        KIPWiringDiagramType kIPWiringDiagramType = new KIPWiringDiagramType()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);
        return kIPWiringDiagramType;
    }

    @BeforeEach
    public void initTest() {
        kIPWiringDiagramType = createEntity(em);
    }

    @Test
    @Transactional
    public void createKIPWiringDiagramType() throws Exception {
        int databaseSizeBeforeCreate = kIPWiringDiagramTypeRepository.findAll().size();
        // Create the KIPWiringDiagramType
        restKIPWiringDiagramTypeMockMvc.perform(post("/api/kip-wiring-diagram-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kIPWiringDiagramType)))
            .andExpect(status().isCreated());

        // Validate the KIPWiringDiagramType in the database
        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeCreate + 1);
        KIPWiringDiagramType testKIPWiringDiagramType = kIPWiringDiagramTypeList.get(kIPWiringDiagramTypeList.size() - 1);
        assertThat(testKIPWiringDiagramType.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testKIPWiringDiagramType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createKIPWiringDiagramTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = kIPWiringDiagramTypeRepository.findAll().size();

        // Create the KIPWiringDiagramType with an existing ID
        kIPWiringDiagramType.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restKIPWiringDiagramTypeMockMvc.perform(post("/api/kip-wiring-diagram-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kIPWiringDiagramType)))
            .andExpect(status().isBadRequest());

        // Validate the KIPWiringDiagramType in the database
        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = kIPWiringDiagramTypeRepository.findAll().size();
        // set the field null
        kIPWiringDiagramType.setTitle(null);

        // Create the KIPWiringDiagramType, which fails.


        restKIPWiringDiagramTypeMockMvc.perform(post("/api/kip-wiring-diagram-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kIPWiringDiagramType)))
            .andExpect(status().isBadRequest());

        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllKIPWiringDiagramTypes() throws Exception {
        // Initialize the database
        kIPWiringDiagramTypeRepository.saveAndFlush(kIPWiringDiagramType);

        // Get all the kIPWiringDiagramTypeList
        restKIPWiringDiagramTypeMockMvc.perform(get("/api/kip-wiring-diagram-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(kIPWiringDiagramType.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getKIPWiringDiagramType() throws Exception {
        // Initialize the database
        kIPWiringDiagramTypeRepository.saveAndFlush(kIPWiringDiagramType);

        // Get the kIPWiringDiagramType
        restKIPWiringDiagramTypeMockMvc.perform(get("/api/kip-wiring-diagram-types/{id}", kIPWiringDiagramType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(kIPWiringDiagramType.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingKIPWiringDiagramType() throws Exception {
        // Get the kIPWiringDiagramType
        restKIPWiringDiagramTypeMockMvc.perform(get("/api/kip-wiring-diagram-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateKIPWiringDiagramType() throws Exception {
        // Initialize the database
        kIPWiringDiagramTypeService.save(kIPWiringDiagramType);

        int databaseSizeBeforeUpdate = kIPWiringDiagramTypeRepository.findAll().size();

        // Update the kIPWiringDiagramType
        KIPWiringDiagramType updatedKIPWiringDiagramType = kIPWiringDiagramTypeRepository.findById(kIPWiringDiagramType.getId()).get();
        // Disconnect from session so that the updates on updatedKIPWiringDiagramType are not directly saved in db
        em.detach(updatedKIPWiringDiagramType);
        updatedKIPWiringDiagramType
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION);

        restKIPWiringDiagramTypeMockMvc.perform(put("/api/kip-wiring-diagram-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedKIPWiringDiagramType)))
            .andExpect(status().isOk());

        // Validate the KIPWiringDiagramType in the database
        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeUpdate);
        KIPWiringDiagramType testKIPWiringDiagramType = kIPWiringDiagramTypeList.get(kIPWiringDiagramTypeList.size() - 1);
        assertThat(testKIPWiringDiagramType.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testKIPWiringDiagramType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingKIPWiringDiagramType() throws Exception {
        int databaseSizeBeforeUpdate = kIPWiringDiagramTypeRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restKIPWiringDiagramTypeMockMvc.perform(put("/api/kip-wiring-diagram-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(kIPWiringDiagramType)))
            .andExpect(status().isBadRequest());

        // Validate the KIPWiringDiagramType in the database
        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteKIPWiringDiagramType() throws Exception {
        // Initialize the database
        kIPWiringDiagramTypeService.save(kIPWiringDiagramType);

        int databaseSizeBeforeDelete = kIPWiringDiagramTypeRepository.findAll().size();

        // Delete the kIPWiringDiagramType
        restKIPWiringDiagramTypeMockMvc.perform(delete("/api/kip-wiring-diagram-types/{id}", kIPWiringDiagramType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<KIPWiringDiagramType> kIPWiringDiagramTypeList = kIPWiringDiagramTypeRepository.findAll();
        assertThat(kIPWiringDiagramTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
