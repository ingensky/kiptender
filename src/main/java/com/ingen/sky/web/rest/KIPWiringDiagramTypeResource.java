package com.ingen.sky.web.rest;

import com.ingen.sky.domain.KIPWiringDiagramType;
import com.ingen.sky.service.KIPWiringDiagramTypeService;
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
 * REST controller for managing {@link com.ingen.sky.domain.KIPWiringDiagramType}.
 */
@RestController
@RequestMapping("/api")
public class KIPWiringDiagramTypeResource {

    private final Logger log = LoggerFactory.getLogger(KIPWiringDiagramTypeResource.class);

    private static final String ENTITY_NAME = "kIPWiringDiagramType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final KIPWiringDiagramTypeService kIPWiringDiagramTypeService;

    public KIPWiringDiagramTypeResource(KIPWiringDiagramTypeService kIPWiringDiagramTypeService) {
        this.kIPWiringDiagramTypeService = kIPWiringDiagramTypeService;
    }

    /**
     * {@code POST  /kip-wiring-diagram-types} : Create a new kIPWiringDiagramType.
     *
     * @param kIPWiringDiagramType the kIPWiringDiagramType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new kIPWiringDiagramType, or with status {@code 400 (Bad Request)} if the kIPWiringDiagramType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/kip-wiring-diagram-types")
    public ResponseEntity<KIPWiringDiagramType> createKIPWiringDiagramType(@Valid @RequestBody KIPWiringDiagramType kIPWiringDiagramType) throws URISyntaxException {
        log.debug("REST request to save KIPWiringDiagramType : {}", kIPWiringDiagramType);
        if (kIPWiringDiagramType.getId() != null) {
            throw new BadRequestAlertException("A new kIPWiringDiagramType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        KIPWiringDiagramType result = kIPWiringDiagramTypeService.save(kIPWiringDiagramType);
        return ResponseEntity.created(new URI("/api/kip-wiring-diagram-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /kip-wiring-diagram-types} : Updates an existing kIPWiringDiagramType.
     *
     * @param kIPWiringDiagramType the kIPWiringDiagramType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated kIPWiringDiagramType,
     * or with status {@code 400 (Bad Request)} if the kIPWiringDiagramType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the kIPWiringDiagramType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/kip-wiring-diagram-types")
    public ResponseEntity<KIPWiringDiagramType> updateKIPWiringDiagramType(@Valid @RequestBody KIPWiringDiagramType kIPWiringDiagramType) throws URISyntaxException {
        log.debug("REST request to update KIPWiringDiagramType : {}", kIPWiringDiagramType);
        if (kIPWiringDiagramType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        KIPWiringDiagramType result = kIPWiringDiagramTypeService.save(kIPWiringDiagramType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, kIPWiringDiagramType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /kip-wiring-diagram-types} : get all the kIPWiringDiagramTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of kIPWiringDiagramTypes in body.
     */
    @GetMapping("/kip-wiring-diagram-types")
    public ResponseEntity<List<KIPWiringDiagramType>> getAllKIPWiringDiagramTypes(Pageable pageable) {
        log.debug("REST request to get a page of KIPWiringDiagramTypes");
        Page<KIPWiringDiagramType> page = kIPWiringDiagramTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /kip-wiring-diagram-types/:id} : get the "id" kIPWiringDiagramType.
     *
     * @param id the id of the kIPWiringDiagramType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the kIPWiringDiagramType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/kip-wiring-diagram-types/{id}")
    public ResponseEntity<KIPWiringDiagramType> getKIPWiringDiagramType(@PathVariable Long id) {
        log.debug("REST request to get KIPWiringDiagramType : {}", id);
        Optional<KIPWiringDiagramType> kIPWiringDiagramType = kIPWiringDiagramTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(kIPWiringDiagramType);
    }

    /**
     * {@code DELETE  /kip-wiring-diagram-types/:id} : delete the "id" kIPWiringDiagramType.
     *
     * @param id the id of the kIPWiringDiagramType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/kip-wiring-diagram-types/{id}")
    public ResponseEntity<Void> deleteKIPWiringDiagramType(@PathVariable Long id) {
        log.debug("REST request to delete KIPWiringDiagramType : {}", id);

        kIPWiringDiagramTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
