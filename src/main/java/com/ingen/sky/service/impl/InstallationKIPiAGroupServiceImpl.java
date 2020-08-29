package com.ingen.sky.service.impl;

import com.ingen.sky.service.InstallationKIPiAGroupService;
import com.ingen.sky.domain.InstallationKIPiAGroup;
import com.ingen.sky.repository.InstallationKIPiAGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InstallationKIPiAGroup}.
 */
@Service
@Transactional
public class InstallationKIPiAGroupServiceImpl implements InstallationKIPiAGroupService {

    private final Logger log = LoggerFactory.getLogger(InstallationKIPiAGroupServiceImpl.class);

    private final InstallationKIPiAGroupRepository installationKIPiAGroupRepository;

    public InstallationKIPiAGroupServiceImpl(InstallationKIPiAGroupRepository installationKIPiAGroupRepository) {
        this.installationKIPiAGroupRepository = installationKIPiAGroupRepository;
    }

    /**
     * Save a installationKIPiAGroup.
     *
     * @param installationKIPiAGroup the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InstallationKIPiAGroup save(InstallationKIPiAGroup installationKIPiAGroup) {
        log.debug("Request to save InstallationKIPiAGroup : {}", installationKIPiAGroup);
        return installationKIPiAGroupRepository.save(installationKIPiAGroup);
    }

    /**
     * Get all the installationKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InstallationKIPiAGroup> findAll(Pageable pageable) {
        log.debug("Request to get all InstallationKIPiAGroups");
        return installationKIPiAGroupRepository.findAll(pageable);
    }


    /**
     * Get all the installationKIPiAGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<InstallationKIPiAGroup> findAllWithEagerRelationships(Pageable pageable) {
        return installationKIPiAGroupRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one installationKIPiAGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InstallationKIPiAGroup> findOne(Long id) {
        log.debug("Request to get InstallationKIPiAGroup : {}", id);
        return installationKIPiAGroupRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the installationKIPiAGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InstallationKIPiAGroup : {}", id);

        installationKIPiAGroupRepository.deleteById(id);
    }
}
