package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.Tender;
import com.ingen.sky.repository.TenderRepository;
import com.ingen.sky.service.TenderService;

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
 * Integration tests for the {@link TenderResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class TenderResourceIT {

    private static final String DEFAULT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_WORKTRIP = false;
    private static final Boolean UPDATED_WORKTRIP = true;

    private static final Boolean DEFAULT_NEED_SID_STAGE = false;
    private static final Boolean UPDATED_NEED_SID_STAGE = true;

    private static final Boolean DEFAULT_NEED_OTR_STAGE = false;
    private static final Boolean UPDATED_NEED_OTR_STAGE = true;

    @Autowired
    private TenderRepository tenderRepository;

    @Mock
    private TenderRepository tenderRepositoryMock;

    @Mock
    private TenderService tenderServiceMock;

    @Autowired
    private TenderService tenderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTenderMockMvc;

    private Tender tender;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tender createEntity(EntityManager em) {
        Tender tender = new Tender()
            .number(DEFAULT_NUMBER)
            .title(DEFAULT_TITLE)
            .description(DEFAULT_DESCRIPTION)
            .location(DEFAULT_LOCATION)
            .worktrip(DEFAULT_WORKTRIP)
            .needSIDStage(DEFAULT_NEED_SID_STAGE)
            .needOTRStage(DEFAULT_NEED_OTR_STAGE);
        return tender;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tender createUpdatedEntity(EntityManager em) {
        Tender tender = new Tender()
            .number(UPDATED_NUMBER)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .location(UPDATED_LOCATION)
            .worktrip(UPDATED_WORKTRIP)
            .needSIDStage(UPDATED_NEED_SID_STAGE)
            .needOTRStage(UPDATED_NEED_OTR_STAGE);
        return tender;
    }

    @BeforeEach
    public void initTest() {
        tender = createEntity(em);
    }

    @Test
    @Transactional
    public void createTender() throws Exception {
        int databaseSizeBeforeCreate = tenderRepository.findAll().size();
        // Create the Tender
        restTenderMockMvc.perform(post("/api/tenders").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isCreated());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeCreate + 1);
        Tender testTender = tenderList.get(tenderList.size() - 1);
        assertThat(testTender.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testTender.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testTender.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testTender.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testTender.isWorktrip()).isEqualTo(DEFAULT_WORKTRIP);
        assertThat(testTender.isNeedSIDStage()).isEqualTo(DEFAULT_NEED_SID_STAGE);
        assertThat(testTender.isNeedOTRStage()).isEqualTo(DEFAULT_NEED_OTR_STAGE);
    }

    @Test
    @Transactional
    public void createTenderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tenderRepository.findAll().size();

        // Create the Tender with an existing ID
        tender.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTenderMockMvc.perform(post("/api/tenders").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isBadRequest());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = tenderRepository.findAll().size();
        // set the field null
        tender.setTitle(null);

        // Create the Tender, which fails.


        restTenderMockMvc.perform(post("/api/tenders").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isBadRequest());

        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTenders() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        // Get all the tenderList
        restTenderMockMvc.perform(get("/api/tenders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tender.getId().intValue())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].location").value(hasItem(DEFAULT_LOCATION)))
            .andExpect(jsonPath("$.[*].worktrip").value(hasItem(DEFAULT_WORKTRIP.booleanValue())))
            .andExpect(jsonPath("$.[*].needSIDStage").value(hasItem(DEFAULT_NEED_SID_STAGE.booleanValue())))
            .andExpect(jsonPath("$.[*].needOTRStage").value(hasItem(DEFAULT_NEED_OTR_STAGE.booleanValue())));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllTendersWithEagerRelationshipsIsEnabled() throws Exception {
        when(tenderServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTenderMockMvc.perform(get("/api/tenders?eagerload=true"))
            .andExpect(status().isOk());

        verify(tenderServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllTendersWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(tenderServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTenderMockMvc.perform(get("/api/tenders?eagerload=true"))
            .andExpect(status().isOk());

        verify(tenderServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getTender() throws Exception {
        // Initialize the database
        tenderRepository.saveAndFlush(tender);

        // Get the tender
        restTenderMockMvc.perform(get("/api/tenders/{id}", tender.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tender.getId().intValue()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION))
            .andExpect(jsonPath("$.worktrip").value(DEFAULT_WORKTRIP.booleanValue()))
            .andExpect(jsonPath("$.needSIDStage").value(DEFAULT_NEED_SID_STAGE.booleanValue()))
            .andExpect(jsonPath("$.needOTRStage").value(DEFAULT_NEED_OTR_STAGE.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingTender() throws Exception {
        // Get the tender
        restTenderMockMvc.perform(get("/api/tenders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTender() throws Exception {
        // Initialize the database
        tenderService.save(tender);

        int databaseSizeBeforeUpdate = tenderRepository.findAll().size();

        // Update the tender
        Tender updatedTender = tenderRepository.findById(tender.getId()).get();
        // Disconnect from session so that the updates on updatedTender are not directly saved in db
        em.detach(updatedTender);
        updatedTender
            .number(UPDATED_NUMBER)
            .title(UPDATED_TITLE)
            .description(UPDATED_DESCRIPTION)
            .location(UPDATED_LOCATION)
            .worktrip(UPDATED_WORKTRIP)
            .needSIDStage(UPDATED_NEED_SID_STAGE)
            .needOTRStage(UPDATED_NEED_OTR_STAGE);

        restTenderMockMvc.perform(put("/api/tenders").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedTender)))
            .andExpect(status().isOk());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeUpdate);
        Tender testTender = tenderList.get(tenderList.size() - 1);
        assertThat(testTender.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testTender.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testTender.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testTender.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTender.isWorktrip()).isEqualTo(UPDATED_WORKTRIP);
        assertThat(testTender.isNeedSIDStage()).isEqualTo(UPDATED_NEED_SID_STAGE);
        assertThat(testTender.isNeedOTRStage()).isEqualTo(UPDATED_NEED_OTR_STAGE);
    }

    @Test
    @Transactional
    public void updateNonExistingTender() throws Exception {
        int databaseSizeBeforeUpdate = tenderRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTenderMockMvc.perform(put("/api/tenders").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tender)))
            .andExpect(status().isBadRequest());

        // Validate the Tender in the database
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTender() throws Exception {
        // Initialize the database
        tenderService.save(tender);

        int databaseSizeBeforeDelete = tenderRepository.findAll().size();

        // Delete the tender
        restTenderMockMvc.perform(delete("/api/tenders/{id}", tender.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tender> tenderList = tenderRepository.findAll();
        assertThat(tenderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
