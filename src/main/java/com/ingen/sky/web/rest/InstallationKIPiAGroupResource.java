package com.ingen.sky.web.rest;

import com.ingen.sky.domain.InstallationKIPiAGroup;
import com.ingen.sky.service.InstallationKIPiAGroupService;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ingen.sky.domain.InstallationKIPiAGroup}.
 */
@RestController
@RequestMapping("/api")
public class InstallationKIPiAGroupResource {

    private final Logger log = LoggerFactory.getLogger(InstallationKIPiAGroupResource.class);

    private static final String ENTITY_NAME = "installationKIPiAGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstallationKIPiAGroupService installationKIPiAGroupService;

    public InstallationKIPiAGroupResource(InstallationKIPiAGroupService installationKIPiAGroupService) {
        this.installationKIPiAGroupService = installationKIPiAGroupService;
    }

    /**
     * {@code POST  /installation-ki-pi-a-groups} : Create a new installationKIPiAGroup.
     *
     * @param installationKIPiAGroup the installationKIPiAGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new installationKIPiAGroup, or with status {@code 400 (Bad Request)} if the installationKIPiAGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/installation-ki-pi-a-groups")
    public ResponseEntity<InstallationKIPiAGroup> createInstallationKIPiAGroup(@RequestBody InstallationKIPiAGroup installationKIPiAGroup) throws URISyntaxException {
        log.debug("REST request to save InstallationKIPiAGroup : {}", installationKIPiAGroup);
        if (installationKIPiAGroup.getId() != null) {
            throw new BadRequestAlertException("A new installationKIPiAGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstallationKIPiAGroup result = installationKIPiAGroupService.save(installationKIPiAGroup);
        return ResponseEntity.created(new URI("/api/installation-ki-pi-a-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /installation-ki-pi-a-groups} : Updates an existing installationKIPiAGroup.
     *
     * @param installationKIPiAGroup the installationKIPiAGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated installationKIPiAGroup,
     * or with status {@code 400 (Bad Request)} if the installationKIPiAGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the installationKIPiAGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/installation-ki-pi-a-groups")
    public ResponseEntity<InstallationKIPiAGroup> updateInstallationKIPiAGroup(@RequestBody InstallationKIPiAGroup installationKIPiAGroup) throws URISyntaxException {
        log.debug("REST request to update InstallationKIPiAGroup : {}", installationKIPiAGroup);
        if (installationKIPiAGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstallationKIPiAGroup result = installationKIPiAGroupService.save(installationKIPiAGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, installationKIPiAGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /installation-ki-pi-a-groups} : get all the installationKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of installationKIPiAGroups in body.
     */
    @GetMapping("/installation-ki-pi-a-groups")
    public ResponseEntity<List<InstallationKIPiAGroup>> getAllInstallationKIPiAGroups(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of InstallationKIPiAGroups");
        Page<InstallationKIPiAGroup> page;
        if (eagerload) {
            page = installationKIPiAGroupService.findAllWithEagerRelationships(pageable);
        } else {
            page = installationKIPiAGroupService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /installation-ki-pi-a-groups/:id} : get the "id" installationKIPiAGroup.
     *
     * @param id the id of the installationKIPiAGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the installationKIPiAGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/installation-ki-pi-a-groups/{id}")
    public ResponseEntity<InstallationKIPiAGroup> getInstallationKIPiAGroup(@PathVariable Long id) {
        log.debug("REST request to get InstallationKIPiAGroup : {}", id);
        Optional<InstallationKIPiAGroup> installationKIPiAGroup = installationKIPiAGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(installationKIPiAGroup);
    }

    /**
     * {@code DELETE  /installation-ki-pi-a-groups/:id} : delete the "id" installationKIPiAGroup.
     *
     * @param id the id of the installationKIPiAGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/installation-ki-pi-a-groups/{id}")
    public ResponseEntity<Void> deleteInstallationKIPiAGroup(@PathVariable Long id) {
        log.debug("REST request to delete InstallationKIPiAGroup : {}", id);

        installationKIPiAGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
