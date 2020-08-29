package com.ingen.sky.service;

import com.ingen.sky.domain.KIPWiringDiagramType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link KIPWiringDiagramType}.
 */
public interface KIPWiringDiagramTypeService {

    /**
     * Save a kIPWiringDiagramType.
     *
     * @param kIPWiringDiagramType the entity to save.
     * @return the persisted entity.
     */
    KIPWiringDiagramType save(KIPWiringDiagramType kIPWiringDiagramType);

    /**
     * Get all the kIPWiringDiagramTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<KIPWiringDiagramType> findAll(Pageable pageable);


    /**
     * Get the "id" kIPWiringDiagramType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<KIPWiringDiagramType> findOne(Long id);

    /**
     * Delete the "id" kIPWiringDiagramType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
