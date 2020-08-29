package com.ingen.sky.service;

import com.ingen.sky.domain.UnitKIPiAGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link UnitKIPiAGroup}.
 */
public interface UnitKIPiAGroupService {

    /**
     * Save a unitKIPiAGroup.
     *
     * @param unitKIPiAGroup the entity to save.
     * @return the persisted entity.
     */
    UnitKIPiAGroup save(UnitKIPiAGroup unitKIPiAGroup);

    /**
     * Get all the unitKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnitKIPiAGroup> findAll(Pageable pageable);

    /**
     * Get all the unitKIPiAGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<UnitKIPiAGroup> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" unitKIPiAGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnitKIPiAGroup> findOne(Long id);

    /**
     * Delete the "id" unitKIPiAGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
