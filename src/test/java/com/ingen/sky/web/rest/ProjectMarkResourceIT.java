package com.ingen.sky.web.rest;

import com.ingen.sky.KiptenderApp;
import com.ingen.sky.domain.ProjectMark;
import com.ingen.sky.repository.ProjectMarkRepository;
import com.ingen.sky.service.ProjectMarkService;

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
 * Integration tests for the {@link ProjectMarkResource} REST controller.
 */
@SpringBootTest(classes = KiptenderApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class ProjectMarkResourceIT {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_MARK = "AAAAAAAAAA";
    private static final String UPDATED_MARK = "BBBBBBBBBB";

    @Autowired
    private ProjectMarkRepository projectMarkRepository;

    @Autowired
    private ProjectMarkService projectMarkService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectMarkMockMvc;

    private ProjectMark projectMark;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectMark createEntity(EntityManager em) {
        ProjectMark projectMark = new ProjectMark()
            .title(DEFAULT_TITLE)
            .mark(DEFAULT_MARK);
        return projectMark;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectMark createUpdatedEntity(EntityManager em) {
        ProjectMark projectMark = new ProjectMark()
            .title(UPDATED_TITLE)
            .mark(UPDATED_MARK);
        return projectMark;
    }

    @BeforeEach
    public void initTest() {
        projectMark = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectMark() throws Exception {
        int databaseSizeBeforeCreate = projectMarkRepository.findAll().size();
        // Create the ProjectMark
        restProjectMarkMockMvc.perform(post("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectMark)))
            .andExpect(status().isCreated());

        // Validate the ProjectMark in the database
        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectMark testProjectMark = projectMarkList.get(projectMarkList.size() - 1);
        assertThat(testProjectMark.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testProjectMark.getMark()).isEqualTo(DEFAULT_MARK);
    }

    @Test
    @Transactional
    public void createProjectMarkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectMarkRepository.findAll().size();

        // Create the ProjectMark with an existing ID
        projectMark.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMarkMockMvc.perform(post("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectMark)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectMark in the database
        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectMarkRepository.findAll().size();
        // set the field null
        projectMark.setTitle(null);

        // Create the ProjectMark, which fails.


        restProjectMarkMockMvc.perform(post("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectMark)))
            .andExpect(status().isBadRequest());

        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarkIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectMarkRepository.findAll().size();
        // set the field null
        projectMark.setMark(null);

        // Create the ProjectMark, which fails.


        restProjectMarkMockMvc.perform(post("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectMark)))
            .andExpect(status().isBadRequest());

        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectMarks() throws Exception {
        // Initialize the database
        projectMarkRepository.saveAndFlush(projectMark);

        // Get all the projectMarkList
        restProjectMarkMockMvc.perform(get("/api/project-marks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectMark.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(DEFAULT_MARK)));
    }
    
    @Test
    @Transactional
    public void getProjectMark() throws Exception {
        // Initialize the database
        projectMarkRepository.saveAndFlush(projectMark);

        // Get the projectMark
        restProjectMarkMockMvc.perform(get("/api/project-marks/{id}", projectMark.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectMark.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.mark").value(DEFAULT_MARK));
    }
    @Test
    @Transactional
    public void getNonExistingProjectMark() throws Exception {
        // Get the projectMark
        restProjectMarkMockMvc.perform(get("/api/project-marks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectMark() throws Exception {
        // Initialize the database
        projectMarkService.save(projectMark);

        int databaseSizeBeforeUpdate = projectMarkRepository.findAll().size();

        // Update the projectMark
        ProjectMark updatedProjectMark = projectMarkRepository.findById(projectMark.getId()).get();
        // Disconnect from session so that the updates on updatedProjectMark are not directly saved in db
        em.detach(updatedProjectMark);
        updatedProjectMark
            .title(UPDATED_TITLE)
            .mark(UPDATED_MARK);

        restProjectMarkMockMvc.perform(put("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedProjectMark)))
            .andExpect(status().isOk());

        // Validate the ProjectMark in the database
        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeUpdate);
        ProjectMark testProjectMark = projectMarkList.get(projectMarkList.size() - 1);
        assertThat(testProjectMark.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testProjectMark.getMark()).isEqualTo(UPDATED_MARK);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectMark() throws Exception {
        int databaseSizeBeforeUpdate = projectMarkRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectMarkMockMvc.perform(put("/api/project-marks").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(projectMark)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectMark in the database
        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProjectMark() throws Exception {
        // Initialize the database
        projectMarkService.save(projectMark);

        int databaseSizeBeforeDelete = projectMarkRepository.findAll().size();

        // Delete the projectMark
        restProjectMarkMockMvc.perform(delete("/api/project-marks/{id}", projectMark.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ProjectMark> projectMarkList = projectMarkRepository.findAll();
        assertThat(projectMarkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
