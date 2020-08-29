package com.ingen.sky.service;

import com.ingen.sky.domain.UnitKIPiA;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link UnitKIPiA}.
 */
public interface UnitKIPiAService {

    /**
     * Save a unitKIPiA.
     *
     * @param unitKIPiA the entity to save.
     * @return the persisted entity.
     */
    UnitKIPiA save(UnitKIPiA unitKIPiA);

    /**
     * Get all the unitKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnitKIPiA> findAll(Pageable pageable);


    /**
     * Get the "id" unitKIPiA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnitKIPiA> findOne(Long id);

    /**
     * Delete the "id" unitKIPiA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
