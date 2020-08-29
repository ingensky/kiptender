package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.UnitKIPiA;
import com.ingen.sky.repository.UnitKIPiARepository;
import com.ingen.sky.service.UnitKIPiAService;

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
 * Integration tests for the {@link UnitKIPiAResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class UnitKIPiAResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SYSTEM_RSUPAZ = false;
    private static final Boolean UPDATED_SYSTEM_RSUPAZ = true;

    private static final Boolean DEFAULT_ROOT_PLAN_PRESENCE = false;
    private static final Boolean UPDATED_ROOT_PLAN_PRESENCE = true;

    private static final Boolean DEFAULT_NEED_PROJECT_ORDER = false;
    private static final Boolean UPDATED_NEED_PROJECT_ORDER = true;

    private static final Boolean DEFAULT_NEED_PROJECT_CALCULATION = false;
    private static final Boolean UPDATED_NEED_PROJECT_CALCULATION = true;

    private static final Boolean DEFAULT_NEED_ELECTRICAL_HEATING = false;
    private static final Boolean UPDATED_NEED_ELECTRICAL_HEATING = true;

    private static final Integer DEFAULT_NUMBER_AI = 1;
    private static final Integer UPDATED_NUMBER_AI = 2;

    private static final Integer DEFAULT_NUMBER_AO = 1;
    private static final Integer UPDATED_NUMBER_AO = 2;

    private static final Integer DEFAULT_NUMBER_DI = 1;
    private static final Integer UPDATED_NUMBER_DI = 2;

    private static final Integer DEFAULT_NUMBER_DO = 1;
    private static final Integer UPDATED_NUMBER_DO = 2;

    private static final Double DEFAULT_BASIC_LABOR_INPUT = 1D;
    private static final Double UPDATED_BASIC_LABOR_INPUT = 2D;

    @Autowired
    private UnitKIPiARepository unitKIPiARepository;

    @Autowired
    private UnitKIPiAService unitKIPiAService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnitKIPiAMockMvc;

    private UnitKIPiA unitKIPiA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitKIPiA createEntity(EntityManager em) {
        UnitKIPiA unitKIPiA = new UnitKIPiA()
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .systemRSUPAZ(DEFAULT_SYSTEM_RSUPAZ)
            .rootPlanPresence(DEFAULT_ROOT_PLAN_PRESENCE)
            .needProjectOrder(DEFAULT_NEED_PROJECT_ORDER)
            .needProjectCalculation(DEFAULT_NEED_PROJECT_CALCULATION)
            .needElectricalHeating(DEFAULT_NEED_ELECTRICAL_HEATING)
            .numberAI(DEFAULT_NUMBER_AI)
            .numberAO(DEFAULT_NUMBER_AO)
            .numberDI(DEFAULT_NUMBER_DI)
            .numberDO(DEFAULT_NUMBER_DO)
            .basicLaborInput(DEFAULT_BASIC_LABOR_INPUT);
        return unitKIPiA;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnitKIPiA createUpdatedEntity(EntityManager em) {
        UnitKIPiA unitKIPiA = new UnitKIPiA()
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .systemRSUPAZ(UPDATED_SYSTEM_RSUPAZ)
            .rootPlanPresence(UPDATED_ROOT_PLAN_PRESENCE)
            .needProjectOrder(UPDATED_NEED_PROJECT_ORDER)
            .needProjectCalculation(UPDATED_NEED_PROJECT_CALCULATION)
            .needElectricalHeating(UPDATED_NEED_ELECTRICAL_HEATING)
            .numberAI(UPDATED_NUMBER_AI)
            .numberAO(UPDATED_NUMBER_AO)
            .numberDI(UPDATED_NUMBER_DI)
            .numberDO(UPDATED_NUMBER_DO)
            .basicLaborInput(UPDATED_BASIC_LABOR_INPUT);
        return unitKIPiA;
    }

    @BeforeEach
    public void initTest() {
        unitKIPiA = createEntity(em);
    }

    @Test
    @Transactional
    public void createUnitKIPiA() throws Exception {
        int databaseSizeBeforeCreate = unitKIPiARepository.findAll().size();
        // Create the UnitKIPiA
        restUnitKIPiAMockMvc.perform(post("/api/unit-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiA)))
            .andExpect(status().isCreated());

        // Validate the UnitKIPiA in the database
        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeCreate + 1);
        UnitKIPiA testUnitKIPiA = unitKIPiAList.get(unitKIPiAList.size() - 1);
        assertThat(testUnitKIPiA.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testUnitKIPiA.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testUnitKIPiA.isSystemRSUPAZ()).isEqualTo(DEFAULT_SYSTEM_RSUPAZ);
        assertThat(testUnitKIPiA.isRootPlanPresence()).isEqualTo(DEFAULT_ROOT_PLAN_PRESENCE);
        assertThat(testUnitKIPiA.isNeedProjectOrder()).isEqualTo(DEFAULT_NEED_PROJECT_ORDER);
        assertThat(testUnitKIPiA.isNeedProjectCalculation()).isEqualTo(DEFAULT_NEED_PROJECT_CALCULATION);
        assertThat(testUnitKIPiA.isNeedElectricalHeating()).isEqualTo(DEFAULT_NEED_ELECTRICAL_HEATING);
        assertThat(testUnitKIPiA.getNumberAI()).isEqualTo(DEFAULT_NUMBER_AI);
        assertThat(testUnitKIPiA.getNumberAO()).isEqualTo(DEFAULT_NUMBER_AO);
        assertThat(testUnitKIPiA.getNumberDI()).isEqualTo(DEFAULT_NUMBER_DI);
        assertThat(testUnitKIPiA.getNumberDO()).isEqualTo(DEFAULT_NUMBER_DO);
        assertThat(testUnitKIPiA.getBasicLaborInput()).isEqualTo(DEFAULT_BASIC_LABOR_INPUT);
    }

    @Test
    @Transactional
    public void createUnitKIPiAWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = unitKIPiARepository.findAll().size();

        // Create the UnitKIPiA with an existing ID
        unitKIPiA.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnitKIPiAMockMvc.perform(post("/api/unit-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiA)))
            .andExpect(status().isBadRequest());

        // Validate the UnitKIPiA in the database
        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = unitKIPiARepository.findAll().size();
        // set the field null
        unitKIPiA.setTitle(null);

        // Create the UnitKIPiA, which fails.


        restUnitKIPiAMockMvc.perform(post("/api/unit-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiA)))
            .andExpect(status().isBadRequest());

        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUnitKIPiAS() throws Exception {
        // Initialize the database
        unitKIPiARepository.saveAndFlush(unitKIPiA);

        // Get all the unitKIPiAList
        restUnitKIPiAMockMvc.perform(get("/api/unit-ki-pi-as?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unitKIPiA.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].systemRSUPAZ").value(hasItem(DEFAULT_SYSTEM_RSUPAZ.booleanValue())))
            .andExpect(jsonPath("$.[*].rootPlanPresence").value(hasItem(DEFAULT_ROOT_PLAN_PRESENCE.booleanValue())))
            .andExpect(jsonPath("$.[*].needProjectOrder").value(hasItem(DEFAULT_NEED_PROJECT_ORDER.booleanValue())))
            .andExpect(jsonPath("$.[*].needProjectCalculation").value(hasItem(DEFAULT_NEED_PROJECT_CALCULATION.booleanValue())))
            .andExpect(jsonPath("$.[*].needElectricalHeating").value(hasItem(DEFAULT_NEED_ELECTRICAL_HEATING.booleanValue())))
            .andExpect(jsonPath("$.[*].numberAI").value(hasItem(DEFAULT_NUMBER_AI)))
            .andExpect(jsonPath("$.[*].numberAO").value(hasItem(DEFAULT_NUMBER_AO)))
            .andExpect(jsonPath("$.[*].numberDI").value(hasItem(DEFAULT_NUMBER_DI)))
            .andExpect(jsonPath("$.[*].numberDO").value(hasItem(DEFAULT_NUMBER_DO)))
            .andExpect(jsonPath("$.[*].basicLaborInput").value(hasItem(DEFAULT_BASIC_LABOR_INPUT.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getUnitKIPiA() throws Exception {
        // Initialize the database
        unitKIPiARepository.saveAndFlush(unitKIPiA);

        // Get the unitKIPiA
        restUnitKIPiAMockMvc.perform(get("/api/unit-ki-pi-as/{id}", unitKIPiA.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unitKIPiA.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.systemRSUPAZ").value(DEFAULT_SYSTEM_RSUPAZ.booleanValue()))
            .andExpect(jsonPath("$.rootPlanPresence").value(DEFAULT_ROOT_PLAN_PRESENCE.booleanValue()))
            .andExpect(jsonPath("$.needProjectOrder").value(DEFAULT_NEED_PROJECT_ORDER.booleanValue()))
            .andExpect(jsonPath("$.needProjectCalculation").value(DEFAULT_NEED_PROJECT_CALCULATION.booleanValue()))
            .andExpect(jsonPath("$.needElectricalHeating").value(DEFAULT_NEED_ELECTRICAL_HEATING.booleanValue()))
            .andExpect(jsonPath("$.numberAI").value(DEFAULT_NUMBER_AI))
            .andExpect(jsonPath("$.numberAO").value(DEFAULT_NUMBER_AO))
            .andExpect(jsonPath("$.numberDI").value(DEFAULT_NUMBER_DI))
            .andExpect(jsonPath("$.numberDO").value(DEFAULT_NUMBER_DO))
            .andExpect(jsonPath("$.basicLaborInput").value(DEFAULT_BASIC_LABOR_INPUT.doubleValue()));
    }
    @Test
    @Transactional
    public void getNonExistingUnitKIPiA() throws Exception {
        // Get the unitKIPiA
        restUnitKIPiAMockMvc.perform(get("/api/unit-ki-pi-as/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUnitKIPiA() throws Exception {
        // Initialize the database
        unitKIPiAService.save(unitKIPiA);

        int databaseSizeBeforeUpdate = unitKIPiARepository.findAll().size();

        // Update the unitKIPiA
        UnitKIPiA updatedUnitKIPiA = unitKIPiARepository.findById(unitKIPiA.getId()).get();
        // Disconnect from session so that the updates on updatedUnitKIPiA are not directly saved in db
        em.detach(updatedUnitKIPiA);
        updatedUnitKIPiA
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .systemRSUPAZ(UPDATED_SYSTEM_RSUPAZ)
            .rootPlanPresence(UPDATED_ROOT_PLAN_PRESENCE)
            .needProjectOrder(UPDATED_NEED_PROJECT_ORDER)
            .needProjectCalculation(UPDATED_NEED_PROJECT_CALCULATION)
            .needElectricalHeating(UPDATED_NEED_ELECTRICAL_HEATING)
            .numberAI(UPDATED_NUMBER_AI)
            .numberAO(UPDATED_NUMBER_AO)
            .numberDI(UPDATED_NUMBER_DI)
            .numberDO(UPDATED_NUMBER_DO)
            .basicLaborInput(UPDATED_BASIC_LABOR_INPUT);

        restUnitKIPiAMockMvc.perform(put("/api/unit-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedUnitKIPiA)))
            .andExpect(status().isOk());

        // Validate the UnitKIPiA in the database
        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeUpdate);
        UnitKIPiA testUnitKIPiA = unitKIPiAList.get(unitKIPiAList.size() - 1);
        assertThat(testUnitKIPiA.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testUnitKIPiA.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testUnitKIPiA.isSystemRSUPAZ()).isEqualTo(UPDATED_SYSTEM_RSUPAZ);
        assertThat(testUnitKIPiA.isRootPlanPresence()).isEqualTo(UPDATED_ROOT_PLAN_PRESENCE);
        assertThat(testUnitKIPiA.isNeedProjectOrder()).isEqualTo(UPDATED_NEED_PROJECT_ORDER);
        assertThat(testUnitKIPiA.isNeedProjectCalculation()).isEqualTo(UPDATED_NEED_PROJECT_CALCULATION);
        assertThat(testUnitKIPiA.isNeedElectricalHeating()).isEqualTo(UPDATED_NEED_ELECTRICAL_HEATING);
        assertThat(testUnitKIPiA.getNumberAI()).isEqualTo(UPDATED_NUMBER_AI);
        assertThat(testUnitKIPiA.getNumberAO()).isEqualTo(UPDATED_NUMBER_AO);
        assertThat(testUnitKIPiA.getNumberDI()).isEqualTo(UPDATED_NUMBER_DI);
        assertThat(testUnitKIPiA.getNumberDO()).isEqualTo(UPDATED_NUMBER_DO);
        assertThat(testUnitKIPiA.getBasicLaborInput()).isEqualTo(UPDATED_BASIC_LABOR_INPUT);
    }

    @Test
    @Transactional
    public void updateNonExistingUnitKIPiA() throws Exception {
        int databaseSizeBeforeUpdate = unitKIPiARepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnitKIPiAMockMvc.perform(put("/api/unit-ki-pi-as").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(unitKIPiA)))
            .andExpect(status().isBadRequest());

        // Validate the UnitKIPiA in the database
        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUnitKIPiA() throws Exception {
        // Initialize the database
        unitKIPiAService.save(unitKIPiA);

        int databaseSizeBeforeDelete = unitKIPiARepository.findAll().size();

        // Delete the unitKIPiA
        restUnitKIPiAMockMvc.perform(delete("/api/unit-ki-pi-as/{id}", unitKIPiA.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UnitKIPiA> unitKIPiAList = unitKIPiARepository.findAll();
        assertThat(unitKIPiAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
