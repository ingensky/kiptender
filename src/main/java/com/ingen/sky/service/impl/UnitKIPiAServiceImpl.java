package com.ingen.sky.service.impl;

import com.ingen.sky.service.UnitKIPiAService;
import com.ingen.sky.domain.UnitKIPiA;
import com.ingen.sky.repository.UnitKIPiARepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnitKIPiA}.
 */
@Service
@Transactional
public class UnitKIPiAServiceImpl implements UnitKIPiAService {

    private final Logger log = LoggerFactory.getLogger(UnitKIPiAServiceImpl.class);

    private final UnitKIPiARepository unitKIPiARepository;

    public UnitKIPiAServiceImpl(UnitKIPiARepository unitKIPiARepository) {
        this.unitKIPiARepository = unitKIPiARepository;
    }

    /**
     * Save a unitKIPiA.
     *
     * @param unitKIPiA the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnitKIPiA save(UnitKIPiA unitKIPiA) {
        log.debug("Request to save UnitKIPiA : {}", unitKIPiA);
        return unitKIPiARepository.save(unitKIPiA);
    }

    /**
     * Get all the unitKIPiAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UnitKIPiA> findAll(Pageable pageable) {
        log.debug("Request to get all UnitKIPiAS");
        return unitKIPiARepository.findAll(pageable);
    }


    /**
     * Get one unitKIPiA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitKIPiA> findOne(Long id) {
        log.debug("Request to get UnitKIPiA : {}", id);
        return unitKIPiARepository.findById(id);
    }

    /**
     * Delete the unitKIPiA by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UnitKIPiA : {}", id);

        unitKIPiARepository.deleteById(id);
    }
}
