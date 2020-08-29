package com.ingen.sky.web.rest;

import com.ingen.sky.domain.UnitKIPiA;
import com.ingen.sky.service.UnitKIPiAService;
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
 * REST controller for managing {@link com.ingen.sky.domain.UnitKIPiA}.
 */
@RestController
@RequestMapping("/api")
public class UnitKIPiAResource {

    private final Logger log = LoggerFactory.getLogger(UnitKIPiAResource.class);

    private static final String ENTITY_NAME = "unitKIPiA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnitKIPiAService unitKIPiAService;

    public UnitKIPiAResource(UnitKIPiAService unitKIPiAService) {
        this.unitKIPiAService = unitKIPiAService;
    }

    /**
     * {@code POST  /unit-ki-pi-as} : Create a new unitKIPiA.
     *
     * @param unitKIPiA the unitKIPiA to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unitKIPiA, or with status {@code 400 (Bad Request)} if the unitKIPiA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unit-ki-pi-as")
    public ResponseEntity<UnitKIPiA> createUnitKIPiA(@Valid @RequestBody UnitKIPiA unitKIPiA) throws URISyntaxException {
        log.debug("REST request to save UnitKIPiA : {}", unitKIPiA);
        if (unitKIPiA.getId() != null) {
            throw new BadRequestAlertException("A new unitKIPiA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitKIPiA result = unitKIPiAService.save(unitKIPiA);
        return ResponseEntity.created(new URI("/api/unit-ki-pi-as/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unit-ki-pi-as} : Updates an existing unitKIPiA.
     *
     * @param unitKIPiA the unitKIPiA to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitKIPiA,
     * or with status {@code 400 (Bad Request)} if the unitKIPiA is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unitKIPiA couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unit-ki-pi-as")
    public ResponseEntity<UnitKIPiA> updateUnitKIPiA(@Valid @RequestBody UnitKIPiA unitKIPiA) throws URISyntaxException {
        log.debug("REST request to update UnitKIPiA : {}", unitKIPiA);
        if (unitKIPiA.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnitKIPiA result = unitKIPiAService.save(unitKIPiA);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitKIPiA.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unit-ki-pi-as} : get all the unitKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unitKIPiAS in body.
     */
    @GetMapping("/unit-ki-pi-as")
    public ResponseEntity<List<UnitKIPiA>> getAllUnitKIPiAS(Pageable pageable) {
        log.debug("REST request to get a page of UnitKIPiAS");
        Page<UnitKIPiA> page = unitKIPiAService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unit-ki-pi-as/:id} : get the "id" unitKIPiA.
     *
     * @param id the id of the unitKIPiA to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unitKIPiA, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unit-ki-pi-as/{id}")
    public ResponseEntity<UnitKIPiA> getUnitKIPiA(@PathVariable Long id) {
        log.debug("REST request to get UnitKIPiA : {}", id);
        Optional<UnitKIPiA> unitKIPiA = unitKIPiAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unitKIPiA);
    }

    /**
     * {@code DELETE  /unit-ki-pi-as/:id} : delete the "id" unitKIPiA.
     *
     * @param id the id of the unitKIPiA to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unit-ki-pi-as/{id}")
    public ResponseEntity<Void> deleteUnitKIPiA(@PathVariable Long id) {
        log.debug("REST request to delete UnitKIPiA : {}", id);

        unitKIPiAService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
