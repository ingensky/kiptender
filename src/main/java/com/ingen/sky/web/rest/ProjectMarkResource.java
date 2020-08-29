package com.ingen.sky.web.rest;

import com.ingen.sky.domain.ProjectMark;
import com.ingen.sky.service.ProjectMarkService;
import com.ingen.sky.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ingen.sky.domain.ProjectMark}.
 */
@RestController
@RequestMapping("/api")
public class ProjectMarkResource {

    private final Logger log = LoggerFactory.getLogger(ProjectMarkResource.class);

    private static final String ENTITY_NAME = "projectMark";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectMarkService projectMarkService;

    public ProjectMarkResource(ProjectMarkService projectMarkService) {
        this.projectMarkService = projectMarkService;
    }

    /**
     * {@code POST  /project-marks} : Create a new projectMark.
     *
     * @param projectMark the projectMark to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectMark, or with status {@code 400 (Bad Request)} if the projectMark has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-marks")
    public ResponseEntity<ProjectMark> createProjectMark(@Valid @RequestBody ProjectMark projectMark) throws URISyntaxException {
        log.debug("REST request to save ProjectMark : {}", projectMark);
        if (projectMark.getId() != null) {
            throw new BadRequestAlertException("A new projectMark cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectMark result = projectMarkService.save(projectMark);
        return ResponseEntity.created(new URI("/api/project-marks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-marks} : Updates an existing projectMark.
     *
     * @param projectMark the projectMark to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectMark,
     * or with status {@code 400 (Bad Request)} if the projectMark is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectMark couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-marks")
    public ResponseEntity<ProjectMark> updateProjectMark(@Valid @RequestBody ProjectMark projectMark) throws URISyntaxException {
        log.debug("REST request to update ProjectMark : {}", projectMark);
        if (projectMark.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ProjectMark result = projectMarkService.save(projectMark);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectMark.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /project-marks} : get all the projectMarks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectMarks in body.
     */
    @GetMapping("/project-marks")
    public ResponseEntity<List<ProjectMark>> getAllProjectMarks(Pageable pageable) {
        log.debug("REST request to get a page of ProjectMarks");
        Page<ProjectMark> page = projectMarkService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /project-marks/:id} : get the "id" projectMark.
     *
     * @param id the id of the projectMark to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectMark, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-marks/{id}")
    public ResponseEntity<ProjectMark> getProjectMark(@PathVariable Long id) {
        log.debug("REST request to get ProjectMark : {}", id);
        Optional<ProjectMark> projectMark = projectMarkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectMark);
    }

    /**
     * {@code DELETE  /project-marks/:id} : delete the "id" projectMark.
     *
     * @param id the id of the projectMark to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-marks/{id}")
    public ResponseEntity<Void> deleteProjectMark(@PathVariable Long id) {
        log.debug("REST request to delete ProjectMark : {}", id);

        projectMarkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
