package com.ingen.sky.web.rest;

import com.ingen.sky.domain.UnitKIPiAGroup;
import com.ingen.sky.service.UnitKIPiAGroupService;
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
 * REST controller for managing {@link com.ingen.sky.domain.UnitKIPiAGroup}.
 */
@RestController
@RequestMapping("/api")
public class UnitKIPiAGroupResource {

    private final Logger log = LoggerFactory.getLogger(UnitKIPiAGroupResource.class);

    private static final String ENTITY_NAME = "unitKIPiAGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UnitKIPiAGroupService unitKIPiAGroupService;

    public UnitKIPiAGroupResource(UnitKIPiAGroupService unitKIPiAGroupService) {
        this.unitKIPiAGroupService = unitKIPiAGroupService;
    }

    /**
     * {@code POST  /unit-ki-pi-a-groups} : Create a new unitKIPiAGroup.
     *
     * @param unitKIPiAGroup the unitKIPiAGroup to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new unitKIPiAGroup, or with status {@code 400 (Bad Request)} if the unitKIPiAGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/unit-ki-pi-a-groups")
    public ResponseEntity<UnitKIPiAGroup> createUnitKIPiAGroup(@RequestBody UnitKIPiAGroup unitKIPiAGroup) throws URISyntaxException {
        log.debug("REST request to save UnitKIPiAGroup : {}", unitKIPiAGroup);
        if (unitKIPiAGroup.getId() != null) {
            throw new BadRequestAlertException("A new unitKIPiAGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UnitKIPiAGroup result = unitKIPiAGroupService.save(unitKIPiAGroup);
        return ResponseEntity.created(new URI("/api/unit-ki-pi-a-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /unit-ki-pi-a-groups} : Updates an existing unitKIPiAGroup.
     *
     * @param unitKIPiAGroup the unitKIPiAGroup to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated unitKIPiAGroup,
     * or with status {@code 400 (Bad Request)} if the unitKIPiAGroup is not valid,
     * or with status {@code 500 (Internal Server Error)} if the unitKIPiAGroup couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/unit-ki-pi-a-groups")
    public ResponseEntity<UnitKIPiAGroup> updateUnitKIPiAGroup(@RequestBody UnitKIPiAGroup unitKIPiAGroup) throws URISyntaxException {
        log.debug("REST request to update UnitKIPiAGroup : {}", unitKIPiAGroup);
        if (unitKIPiAGroup.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UnitKIPiAGroup result = unitKIPiAGroupService.save(unitKIPiAGroup);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, unitKIPiAGroup.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /unit-ki-pi-a-groups} : get all the unitKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of unitKIPiAGroups in body.
     */
    @GetMapping("/unit-ki-pi-a-groups")
    public ResponseEntity<List<UnitKIPiAGroup>> getAllUnitKIPiAGroups(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of UnitKIPiAGroups");
        Page<UnitKIPiAGroup> page;
        if (eagerload) {
            page = unitKIPiAGroupService.findAllWithEagerRelationships(pageable);
        } else {
            page = unitKIPiAGroupService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /unit-ki-pi-a-groups/:id} : get the "id" unitKIPiAGroup.
     *
     * @param id the id of the unitKIPiAGroup to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the unitKIPiAGroup, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/unit-ki-pi-a-groups/{id}")
    public ResponseEntity<UnitKIPiAGroup> getUnitKIPiAGroup(@PathVariable Long id) {
        log.debug("REST request to get UnitKIPiAGroup : {}", id);
        Optional<UnitKIPiAGroup> unitKIPiAGroup = unitKIPiAGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(unitKIPiAGroup);
    }

    /**
     * {@code DELETE  /unit-ki-pi-a-groups/:id} : delete the "id" unitKIPiAGroup.
     *
     * @param id the id of the unitKIPiAGroup to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/unit-ki-pi-a-groups/{id}")
    public ResponseEntity<Void> deleteUnitKIPiAGroup(@PathVariable Long id) {
        log.debug("REST request to delete UnitKIPiAGroup : {}", id);

        unitKIPiAGroupService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
