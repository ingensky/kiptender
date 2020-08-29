package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.InstallationKIPiAGroup;
import com.ingen.sky.repository.InstallationKIPiAGroupRepository;
import com.ingen.sky.service.InstallationKIPiAGroupService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link InstallationKIPiAGroupResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class InstallationKIPiAGroupResourceIT {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    @Autowired
    private InstallationKIPiAGroupRepository installationKIPiAGroupRepository;

    @Mock
    private InstallationKIPiAGroupRepository installationKIPiAGroupRepositoryMock;

    @Mock
    private InstallationKIPiAGroupService installationKIPiAGroupServiceMock;

    @Autowired
    private InstallationKIPiAGroupService installationKIPiAGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInstallationKIPiAGroupMockMvc;

    private InstallationKIPiAGroup installationKIPiAGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallationKIPiAGroup createEntity(EntityManager em) {
        InstallationKIPiAGroup installationKIPiAGroup = new InstallationKIPiAGroup()
            .quantity(DEFAULT_QUANTITY);
        return installationKIPiAGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstallationKIPiAGroup createUpdatedEntity(EntityManager em) {
        InstallationKIPiAGroup installationKIPiAGroup = new InstallationKIPiAGroup()
            .quantity(UPDATED_QUANTITY);
        return installationKIPiAGroup;
    }

    @BeforeEach
    public void initTest() {
        installationKIPiAGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createInstallationKIPiAGroup() throws Exception {
        int databaseSizeBeforeCreate = installationKIPiAGroupRepository.findAll().size();
        // Create the InstallationKIPiAGroup
        restInstallationKIPiAGroupMockMvc.perform(post("/api/installation-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiAGroup)))
            .andExpect(status().isCreated());

        // Validate the InstallationKIPiAGroup in the database
        List<InstallationKIPiAGroup> installationKIPiAGroupList = installationKIPiAGroupRepository.findAll();
        assertThat(installationKIPiAGroupList).hasSize(databaseSizeBeforeCreate + 1);
        InstallationKIPiAGroup testInstallationKIPiAGroup = installationKIPiAGroupList.get(installationKIPiAGroupList.size() - 1);
        assertThat(testInstallationKIPiAGroup.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createInstallationKIPiAGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = installationKIPiAGroupRepository.findAll().size();

        // Create the InstallationKIPiAGroup with an existing ID
        installationKIPiAGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInstallationKIPiAGroupMockMvc.perform(post("/api/installation-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiAGroup)))
            .andExpect(status().isBadRequest());

        // Validate the InstallationKIPiAGroup in the database
        List<InstallationKIPiAGroup> installationKIPiAGroupList = installationKIPiAGroupRepository.findAll();
        assertThat(installationKIPiAGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllInstallationKIPiAGroups() throws Exception {
        // Initialize the database
        installationKIPiAGroupRepository.saveAndFlush(installationKIPiAGroup);

        // Get all the installationKIPiAGroupList
        restInstallationKIPiAGroupMockMvc.perform(get("/api/installation-ki-pi-a-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(installationKIPiAGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllInstallationKIPiAGroupsWithEagerRelationshipsIsEnabled() throws Exception {
        when(installationKIPiAGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInstallationKIPiAGroupMockMvc.perform(get("/api/installation-ki-pi-a-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(installationKIPiAGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllInstallationKIPiAGroupsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(installationKIPiAGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restInstallationKIPiAGroupMockMvc.perform(get("/api/installation-ki-pi-a-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(installationKIPiAGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getInstallationKIPiAGroup() throws Exception {
        // Initialize the database
        installationKIPiAGroupRepository.saveAndFlush(installationKIPiAGroup);

        // Get the installationKIPiAGroup
        restInstallationKIPiAGroupMockMvc.perform(get("/api/installation-ki-pi-a-groups/{id}", installationKIPiAGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(installationKIPiAGroup.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }
    @Test
    @Transactional
    public void getNonExistingInstallationKIPiAGroup() throws Exception {
        // Get the installationKIPiAGroup
        restInstallationKIPiAGroupMockMvc.perform(get("/api/installation-ki-pi-a-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstallationKIPiAGroup() throws Exception {
        // Initialize the database
        installationKIPiAGroupService.save(installationKIPiAGroup);

        int databaseSizeBeforeUpdate = installationKIPiAGroupRepository.findAll().size();

        // Update the installationKIPiAGroup
        InstallationKIPiAGroup updatedInstallationKIPiAGroup = installationKIPiAGroupRepository.findById(installationKIPiAGroup.getId()).get();
        // Disconnect from session so that the updates on updatedInstallationKIPiAGroup are not directly saved in db
        em.detach(updatedInstallationKIPiAGroup);
        updatedInstallationKIPiAGroup
            .quantity(UPDATED_QUANTITY);

        restInstallationKIPiAGroupMockMvc.perform(put("/api/installation-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedInstallationKIPiAGroup)))
            .andExpect(status().isOk());

        // Validate the InstallationKIPiAGroup in the database
        List<InstallationKIPiAGroup> installationKIPiAGroupList = installationKIPiAGroupRepository.findAll();
        assertThat(installationKIPiAGroupList).hasSize(databaseSizeBeforeUpdate);
        InstallationKIPiAGroup testInstallationKIPiAGroup = installationKIPiAGroupList.get(installationKIPiAGroupList.size() - 1);
        assertThat(testInstallationKIPiAGroup.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingInstallationKIPiAGroup() throws Exception {
        int databaseSizeBeforeUpdate = installationKIPiAGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInstallationKIPiAGroupMockMvc.perform(put("/api/installation-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(installationKIPiAGroup)))
            .andExpect(status().isBadRequest());

        // Validate the InstallationKIPiAGroup in the database
        List<InstallationKIPiAGroup> installationKIPiAGroupList = installationKIPiAGroupRepository.findAll();
        assertThat(installationKIPiAGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteInstallationKIPiAGroup() throws Exception {
        // Initialize the database
        installationKIPiAGroupService.save(installationKIPiAGroup);

        int databaseSizeBeforeDelete = installationKIPiAGroupRepository.findAll().size();

        // Delete the installationKIPiAGroup
        restInstallationKIPiAGroupMockMvc.perform(delete("/api/installation-ki-pi-a-groups/{id}", installationKIPiAGroup.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<InstallationKIPiAGroup> installationKIPiAGroupList = installationKIPiAGroupRepository.findAll();
        assertThat(installationKIPiAGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
