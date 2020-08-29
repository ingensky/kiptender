package com.ingen.sky.web.rest;

import com.ingen.sky.domain.InstallationKIPiA;
import com.ingen.sky.service.InstallationKIPiAService;
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
 * REST controller for managing {@link com.ingen.sky.domain.InstallationKIPiA}.
 */
@RestController
@RequestMapping("/api")
public class InstallationKIPiAResource {

    private final Logger log = LoggerFactory.getLogger(InstallationKIPiAResource.class);

    private static final String ENTITY_NAME = "installationKIPiA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstallationKIPiAService installationKIPiAService;

    public InstallationKIPiAResource(InstallationKIPiAService installationKIPiAService) {
        this.installationKIPiAService = installationKIPiAService;
    }

    /**
     * {@code POST  /installation-ki-pi-as} : Create a new installationKIPiA.
     *
     * @param installationKIPiA the installationKIPiA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new installationKIPiA, or with status {@code 400 (Bad Request)} if the installationKIPiA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/installation-ki-pi-as")
    public ResponseEntity<InstallationKIPiA> createInstallationKIPiA(@Valid @RequestBody InstallationKIPiA installationKIPiA) throws URISyntaxException {
        log.debug("REST request to save InstallationKIPiA : {}", installationKIPiA);
        if (installationKIPiA.getId() != null) {
            throw new BadRequestAlertException("A new installationKIPiA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InstallationKIPiA result = installationKIPiAService.save(installationKIPiA);
        return ResponseEntity.created(new URI("/api/installation-ki-pi-as/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /installation-ki-pi-as} : Updates an existing installationKIPiA.
     *
     * @param installationKIPiA the installationKIPiA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated installationKIPiA,
     * or with status {@code 400 (Bad Request)} if the installationKIPiA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the installationKIPiA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/installation-ki-pi-as")
    public ResponseEntity<InstallationKIPiA> updateInstallationKIPiA(@Valid @RequestBody InstallationKIPiA installationKIPiA) throws URISyntaxException {
        log.debug("REST request to update InstallationKIPiA : {}", installationKIPiA);
        if (installationKIPiA.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InstallationKIPiA result = installationKIPiAService.save(installationKIPiA);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, installationKIPiA.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /installation-ki-pi-as} : get all the installationKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of installationKIPiAS in body.
     */
    @GetMapping("/installation-ki-pi-as")
    public ResponseEntity<List<InstallationKIPiA>> getAllInstallationKIPiAS(Pageable pageable) {
        log.debug("REST request to get a page of InstallationKIPiAS");
        Page<InstallationKIPiA> page = installationKIPiAService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /installation-ki-pi-as/:id} : get the "id" installationKIPiA.
     *
     * @param id the id of the installationKIPiA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the installationKIPiA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/installation-ki-pi-as/{id}")
    public ResponseEntity<InstallationKIPiA> getInstallationKIPiA(@PathVariable Long id) {
        log.debug("REST request to get InstallationKIPiA : {}", id);
        Optional<InstallationKIPiA> installationKIPiA = installationKIPiAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(installationKIPiA);
    }

    /**
     * {@code DELETE  /installation-ki-pi-as/:id} : delete the "id" installationKIPiA.
     *
     * @param id the id of the installationKIPiA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/installation-ki-pi-as/{id}")
    public ResponseEntity<Void> deleteInstallationKIPiA(@PathVariable Long id) {
        log.debug("REST request to delete InstallationKIPiA : {}", id);

        installationKIPiAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
