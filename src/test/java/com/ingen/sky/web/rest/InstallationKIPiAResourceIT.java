package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.InstallationKIPiA;
import com.ingen.sky.repository.InstallationKIPiARepository;
import com.ingen.sky.service.InstallationKIPiAService;

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
 * Integration tests for the {@link InstallationKIPiAResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class InstallationKIPiAResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    @Autowired
    private InstallationKIPiARepository installationKIPiARepository;

    @Autowired
    private InstallationKIPiAService installationKIPiAService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstallationKIPiAMockMvc;

    private InstallationKIPiA installationKIPiA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallationKIPiA createEntity(EntityManager em) {
        InstallationKIPiA installationKIPiA = new InstallationKIPiA()
            .title(DEFAULT_TITLE);
        return installationKIPiA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallationKIPiA createUpdatedEntity(EntityManager em) {
        InstallationKIPiA installationKIPiA = new InstallationKIPiA()
            .title(UPDATED_TITLE);
        return installationKIPiA;
    }

    @BeforeEach
    public void initTest() {
        installationKIPiA = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstallationKIPiA() throws Exception {
        int databaseSizeBeforeCreate = installationKIPiARepository.findAll().size();
        // Create the InstallationKIPiA
        restInstallationKIPiAMockMvc.perform(post("/api/installation-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiA)))
            .andExpect(status().isCreated());

        // Validate the InstallationKIPiA in the database
        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeCreate + 1);
        InstallationKIPiA testInstallationKIPiA = installationKIPiAList.get(installationKIPiAList.size() - 1);
        assertThat(testInstallationKIPiA.getTitle()).isEqualTo(DEFAULT_TITLE);
    }

    @Test
    @Transactional
    public void createInstallationKIPiAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = installationKIPiARepository.findAll().size();

        // Create the InstallationKIPiA with an existing ID
        installationKIPiA.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstallationKIPiAMockMvc.perform(post("/api/installation-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiA)))
            .andExpect(status().isBadRequest());

        // Validate the InstallationKIPiA in the database
        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = installationKIPiARepository.findAll().size();
        // set the field null
        installationKIPiA.setTitle(null);

        // Create the InstallationKIPiA, which fails.


        restInstallationKIPiAMockMvc.perform(post("/api/installation-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiA)))
            .andExpect(status().isBadRequest());

        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstallationKIPiAS() throws Exception {
        // Initialize the database
        installationKIPiARepository.saveAndFlush(installationKIPiA);

        // Get all the installationKIPiAList
        restInstallationKIPiAMockMvc.perform(get("/api/installation-ki-pi-as?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(installationKIPiA.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)));
    }
    
    @Test
    @Transactional
    public void getInstallationKIPiA() throws Exception {
        // Initialize the database
        installationKIPiARepository.saveAndFlush(installationKIPiA);

        // Get the installationKIPiA
        restInstallationKIPiAMockMvc.perform(get("/api/installation-ki-pi-as/{id}", installationKIPiA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(installationKIPiA.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE));
    }
    @Test
    @Transactional
    public void getNonExistingInstallationKIPiA() throws Exception {
        // Get the installationKIPiA
        restInstallationKIPiAMockMvc.perform(get("/api/installation-ki-pi-as/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstallationKIPiA() throws Exception {
        // Initialize the database
        installationKIPiAService.save(installationKIPiA);

        int databaseSizeBeforeUpdate = installationKIPiARepository.findAll().size();

        // Update the installationKIPiA
        InstallationKIPiA updatedInstallationKIPiA = installationKIPiARepository.findById(installationKIPiA.getId()).get();
        // Disconnect from session so that the updates on updatedInstallationKIPiA are not directly saved in db
        em.detach(updatedInstallationKIPiA);
        updatedInstallationKIPiA
            .title(UPDATED_TITLE);

        restInstallationKIPiAMockMvc.perform(put("/api/installation-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstallationKIPiA)))
            .andExpect(status().isOk());

        // Validate the InstallationKIPiA in the database
        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeUpdate);
        InstallationKIPiA testInstallationKIPiA = installationKIPiAList.get(installationKIPiAList.size() - 1);
        assertThat(testInstallationKIPiA.getTitle()).isEqualTo(UPDATED_TITLE);
    }

    @Test
    @Transactional
    public void updateNonExistingInstallationKIPiA() throws Exception {
        int databaseSizeBeforeUpdate = installationKIPiARepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstallationKIPiAMockMvc.perform(put("/api/installation-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiA)))
            .andExpect(status().isBadRequest());

        // Validate the InstallationKIPiA in the database
        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstallationKIPiA() throws Exception {
        // Initialize the database
        installationKIPiAService.save(installationKIPiA);

        int databaseSizeBeforeDelete = installationKIPiARepository.findAll().size();

        // Delete the installationKIPiA
        restInstallationKIPiAMockMvc.perform(delete("/api/installation-ki-pi-as/{id}", installationKIPiA.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstallationKIPiA> installationKIPiAList = installationKIPiARepository.findAll();
        assertThat(installationKIPiAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
