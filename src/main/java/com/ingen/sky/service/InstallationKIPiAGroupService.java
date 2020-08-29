package com.ingen.sky.service;

import com.ingen.sky.domain.InstallationKIPiAGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link InstallationKIPiAGroup}.
 */
public interface InstallationKIPiAGroupService {

    /**
     * Save a installationKIPiAGroup.
     *
     * @param installationKIPiAGroup the entity to save.
     * @return the persisted entity.
     */
    InstallationKIPiAGroup save(InstallationKIPiAGroup installationKIPiAGroup);

    /**
     * Get all the installationKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InstallationKIPiAGroup> findAll(Pageable pageable);

    /**
     * Get all the installationKIPiAGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<InstallationKIPiAGroup> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" installationKIPiAGroup.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InstallationKIPiAGroup> findOne(Long id);

    /**
     * Delete the "id" installationKIPiAGroup.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
