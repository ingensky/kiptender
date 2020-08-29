package com.ingen.sky.service.impl;

import com.ingen.sky.service.InstallationKIPiAService;
import com.ingen.sky.domain.InstallationKIPiA;
import com.ingen.sky.repository.InstallationKIPiARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InstallationKIPiA}.
 */
@Service
@Transactional
public class InstallationKIPiAServiceImpl implements InstallationKIPiAService {

    private final Logger log = LoggerFactory.getLogger(InstallationKIPiAServiceImpl.class);

    private final InstallationKIPiARepository installationKIPiARepository;

    public InstallationKIPiAServiceImpl(InstallationKIPiARepository installationKIPiARepository) {
        this.installationKIPiARepository = installationKIPiARepository;
    }

    /**
     * Save a installationKIPiA.
     *
     * @param installationKIPiA the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InstallationKIPiA save(InstallationKIPiA installationKIPiA) {
        log.debug("Request to save InstallationKIPiA : {}", installationKIPiA);
        return installationKIPiARepository.save(installationKIPiA);
    }

    /**
     * Get all the installationKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InstallationKIPiA> findAll(Pageable pageable) {
        log.debug("Request to get all InstallationKIPiAS");
        return installationKIPiARepository.findAll(pageable);
    }


    /**
     * Get one installationKIPiA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InstallationKIPiA> findOne(Long id) {
        log.debug("Request to get InstallationKIPiA : {}", id);
        return installationKIPiARepository.findById(id);
    }

    /**
     * Delete the installationKIPiA by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InstallationKIPiA : {}", id);

        installationKIPiARepository.deleteById(id);
    }
}
