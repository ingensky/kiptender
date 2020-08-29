package com.ingen.sky.service.impl;

import com.ingen.sky.service.UnitKIPiAGroupService;
import com.ingen.sky.domain.UnitKIPiAGroup;
import com.ingen.sky.repository.UnitKIPiAGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UnitKIPiAGroup}.
 */
@Service
@Transactional
public class UnitKIPiAGroupServiceImpl implements UnitKIPiAGroupService {

    private final Logger log = LoggerFactory.getLogger(UnitKIPiAGroupServiceImpl.class);

    private final UnitKIPiAGroupRepository unitKIPiAGroupRepository;

    public UnitKIPiAGroupServiceImpl(UnitKIPiAGroupRepository unitKIPiAGroupRepository) {
        this.unitKIPiAGroupRepository = unitKIPiAGroupRepository;
    }

    /**
     * Save a unitKIPiAGroup.
     *
     * @param unitKIPiAGroup the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UnitKIPiAGroup save(UnitKIPiAGroup unitKIPiAGroup) {
        log.debug("Request to save UnitKIPiAGroup : {}", unitKIPiAGroup);
        return unitKIPiAGroupRepository.save(unitKIPiAGroup);
    }

    /**
     * Get all the unitKIPiAGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UnitKIPiAGroup> findAll(Pageable pageable) {
        log.debug("Request to get all UnitKIPiAGroups");
        return unitKIPiAGroupRepository.findAll(pageable);
    }


    /**
     * Get all the unitKIPiAGroups with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<UnitKIPiAGroup> findAllWithEagerRelationships(Pageable pageable) {
        return unitKIPiAGroupRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one unitKIPiAGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UnitKIPiAGroup> findOne(Long id) {
        log.debug("Request to get UnitKIPiAGroup : {}", id);
        return unitKIPiAGroupRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the unitKIPiAGroup by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UnitKIPiAGroup : {}", id);

        unitKIPiAGroupRepository.deleteById(id);
    }
}
