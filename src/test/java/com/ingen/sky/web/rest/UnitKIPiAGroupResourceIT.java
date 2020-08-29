package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.UnitKIPiAGroup;
import com.ingen.sky.repository.UnitKIPiAGroupRepository;
import com.ingen.sky.service.UnitKIPiAGroupService;

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
 * Integration tests for the {@link UnitKIPiAGroupResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class UnitKIPiAGroupResourceIT {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    @Autowired
    private UnitKIPiAGroupRepository unitKIPiAGroupRepository;

    @Mock
    private UnitKIPiAGroupRepository unitKIPiAGroupRepositoryMock;

    @Mock
    private UnitKIPiAGroupService unitKIPiAGroupServiceMock;

    @Autowired
    private UnitKIPiAGroupService unitKIPiAGroupService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitKIPiAGroupMockMvc;

    private UnitKIPiAGroup unitKIPiAGroup;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitKIPiAGroup createEntity(EntityManager em) {
        UnitKIPiAGroup unitKIPiAGroup = new UnitKIPiAGroup()
            .quantity(DEFAULT_QUANTITY);
        return unitKIPiAGroup;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitKIPiAGroup createUpdatedEntity(EntityManager em) {
        UnitKIPiAGroup unitKIPiAGroup = new UnitKIPiAGroup()
            .quantity(UPDATED_QUANTITY);
        return unitKIPiAGroup;
    }

    @BeforeEach
    public void initTest() {
        unitKIPiAGroup = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitKIPiAGroup() throws Exception {
        int databaseSizeBeforeCreate = unitKIPiAGroupRepository.findAll().size();
        // Create the UnitKIPiAGroup
        restUnitKIPiAGroupMockMvc.perform(post("/api/unit-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiAGroup)))
            .andExpect(status().isCreated());

        // Validate the UnitKIPiAGroup in the database
        List<UnitKIPiAGroup> unitKIPiAGroupList = unitKIPiAGroupRepository.findAll();
        assertThat(unitKIPiAGroupList).hasSize(databaseSizeBeforeCreate + 1);
        UnitKIPiAGroup testUnitKIPiAGroup = unitKIPiAGroupList.get(unitKIPiAGroupList.size() - 1);
        assertThat(testUnitKIPiAGroup.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createUnitKIPiAGroupWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitKIPiAGroupRepository.findAll().size();

        // Create the UnitKIPiAGroup with an existing ID
        unitKIPiAGroup.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitKIPiAGroupMockMvc.perform(post("/api/unit-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiAGroup)))
            .andExpect(status().isBadRequest());

        // Validate the UnitKIPiAGroup in the database
        List<UnitKIPiAGroup> unitKIPiAGroupList = unitKIPiAGroupRepository.findAll();
        assertThat(unitKIPiAGroupList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUnitKIPiAGroups() throws Exception {
        // Initialize the database
        unitKIPiAGroupRepository.saveAndFlush(unitKIPiAGroup);

        // Get all the unitKIPiAGroupList
        restUnitKIPiAGroupMockMvc.perform(get("/api/unit-ki-pi-a-groups?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitKIPiAGroup.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllUnitKIPiAGroupsWithEagerRelationshipsIsEnabled() throws Exception {
        when(unitKIPiAGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnitKIPiAGroupMockMvc.perform(get("/api/unit-ki-pi-a-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(unitKIPiAGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllUnitKIPiAGroupsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(unitKIPiAGroupServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restUnitKIPiAGroupMockMvc.perform(get("/api/unit-ki-pi-a-groups?eagerload=true"))
            .andExpect(status().isOk());

        verify(unitKIPiAGroupServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getUnitKIPiAGroup() throws Exception {
        // Initialize the database
        unitKIPiAGroupRepository.saveAndFlush(unitKIPiAGroup);

        // Get the unitKIPiAGroup
        restUnitKIPiAGroupMockMvc.perform(get("/api/unit-ki-pi-a-groups/{id}", unitKIPiAGroup.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unitKIPiAGroup.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }
    @Test
    @Transactional
    public void getNonExistingUnitKIPiAGroup() throws Exception {
        // Get the unitKIPiAGroup
        restUnitKIPiAGroupMockMvc.perform(get("/api/unit-ki-pi-a-groups/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitKIPiAGroup() throws Exception {
        // Initialize the database
        unitKIPiAGroupService.save(unitKIPiAGroup);

        int databaseSizeBeforeUpdate = unitKIPiAGroupRepository.findAll().size();

        // Update the unitKIPiAGroup
        UnitKIPiAGroup updatedUnitKIPiAGroup = unitKIPiAGroupRepository.findById(unitKIPiAGroup.getId()).get();
        // Disconnect from session so that the updates on updatedUnitKIPiAGroup are not directly saved in db
        em.detach(updatedUnitKIPiAGroup);
        updatedUnitKIPiAGroup
            .quantity(UPDATED_QUANTITY);

        restUnitKIPiAGroupMockMvc.perform(put("/api/unit-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUnitKIPiAGroup)))
            .andExpect(status().isOk());

        // Validate the UnitKIPiAGroup in the database
        List<UnitKIPiAGroup> unitKIPiAGroupList = unitKIPiAGroupRepository.findAll();
        assertThat(unitKIPiAGroupList).hasSize(databaseSizeBeforeUpdate);
        UnitKIPiAGroup testUnitKIPiAGroup = unitKIPiAGroupList.get(unitKIPiAGroupList.size() - 1);
        assertThat(testUnitKIPiAGroup.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitKIPiAGroup() throws Exception {
        int databaseSizeBeforeUpdate = unitKIPiAGroupRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitKIPiAGroupMockMvc.perform(put("/api/unit-ki-pi-a-groups").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiAGroup)))
            .andExpect(status().isBadRequest());

        // Validate the UnitKIPiAGroup in the database
        List<UnitKIPiAGroup> unitKIPiAGroupList = unitKIPiAGroupRepository.findAll();
        assertThat(unitKIPiAGroupList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnitKIPiAGroup() throws Exception {
        // Initialize the database
        unitKIPiAGroupService.save(unitKIPiAGroup);

        int databaseSizeBeforeDelete = unitKIPiAGroupRepository.findAll().size();

        // Delete the unitKIPiAGroup
        restUnitKIPiAGroupMockMvc.perform(delete("/api/unit-ki-pi-a-groups/{id}", unitKIPiAGroup.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnitKIPiAGroup> unitKIPiAGroupList = unitKIPiAGroupRepository.findAll();
        assertThat(unitKIPiAGroupList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
