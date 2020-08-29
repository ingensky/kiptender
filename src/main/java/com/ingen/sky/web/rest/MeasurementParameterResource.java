package com.ingen.sky.web.rest;

import com.ingen.sky.domain.MeasurementParameter;
import com.ingen.sky.service.MeasurementParameterService;
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
 * REST controller for managing {@link com.ingen.sky.domain.MeasurementParameter}.
 */
@RestController
@RequestMapping("/api")
public class MeasurementParameterResource {

    private final Logger log = LoggerFactory.getLogger(MeasurementParameterResource.class);

    private static final String ENTITY_NAME = "measurementParameter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeasurementParameterService measurementParameterService;

    public MeasurementParameterResource(MeasurementParameterService measurementParameterService) {
        this.measurementParameterService = measurementParameterService;
    }

    /**
     * {@code POST  /measurement-parameters} : Create a new measurementParameter.
     *
     * @param measurementParameter the measurementParameter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new measurementParameter, or with status {@code 400 (Bad Request)} if the measurementParameter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/measurement-parameters")
    public ResponseEntity<MeasurementParameter> createMeasurementParameter(@RequestBody MeasurementParameter measurementParameter) throws URISyntaxException {
        log.debug("REST request to save MeasurementParameter : {}", measurementParameter);
        if (measurementParameter.getId() != null) {
            throw new BadRequestAlertException("A new measurementParameter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeasurementParameter result = measurementParameterService.save(measurementParameter);
        return ResponseEntity.created(new URI("/api/measurement-parameters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /measurement-parameters} : Updates an existing measurementParameter.
     *
     * @param measurementParameter the measurementParameter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated measurementParameter,
     * or with status {@code 400 (Bad Request)} if the measurementParameter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the measurementParameter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/measurement-parameters")
    public ResponseEntity<MeasurementParameter> updateMeasurementParameter(@RequestBody MeasurementParameter measurementParameter) throws URISyntaxException {
        log.debug("REST request to update MeasurementParameter : {}", measurementParameter);
        if (measurementParameter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeasurementParameter result = measurementParameterService.save(measurementParameter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, measurementParameter.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /measurement-parameters} : get all the measurementParameters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of measurementParameters in body.
     */
    @GetMapping("/measurement-parameters")
    public ResponseEntity<List<MeasurementParameter>> getAllMeasurementParameters(Pageable pageable) {
        log.debug("REST request to get a page of MeasurementParameters");
        Page<MeasurementParameter> page = measurementParameterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /measurement-parameters/:id} : get the "id" measurementParameter.
     *
     * @param id the id of the measurementParameter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the measurementParameter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/measurement-parameters/{id}")
    public ResponseEntity<MeasurementParameter> getMeasurementParameter(@PathVariable Long id) {
        log.debug("REST request to get MeasurementParameter : {}", id);
        Optional<MeasurementParameter> measurementParameter = measurementParameterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(measurementParameter);
    }

    /**
     * {@code DELETE  /measurement-parameters/:id} : delete the "id" measurementParameter.
     *
     * @param id the id of the measurementParameter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/measurement-parameters/{id}")
    public ResponseEntity<Void> deleteMeasurementParameter(@PathVariable Long id) {
        log.debug("REST request to delete MeasurementParameter : {}", id);

        measurementParameterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
