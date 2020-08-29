package com.ingen.sky.service;

import com.ingen.sky.domain.MeasurementParameter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link MeasurementParameter}.
 */
public interface MeasurementParameterService {

    /**
     * Save a measurementParameter.
     *
     * @param measurementParameter the entity to save.
     * @return the persisted entity.
     */
    MeasurementParameter save(MeasurementParameter measurementParameter);

    /**
     * Get all the measurementParameters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MeasurementParameter> findAll(Pageable pageable);


    /**
     * Get the "id" measurementParameter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MeasurementParameter> findOne(Long id);

    /**
     * Delete the "id" measurementParameter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
