package com.ingen.sky.service;

import com.ingen.sky.domain.InstallationKIPiA;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link InstallationKIPiA}.
 */
public interface InstallationKIPiAService {

    /**
     * Save a installationKIPiA.
     *
     * @param installationKIPiA the entity to save.
     * @return the persisted entity.
     */
    InstallationKIPiA save(InstallationKIPiA installationKIPiA);

    /**
     * Get all the installationKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InstallationKIPiA> findAll(Pageable pageable);


    /**
     * Get the "id" installationKIPiA.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InstallationKIPiA> findOne(Long id);

    /**
     * Delete the "id" installationKIPiA.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
