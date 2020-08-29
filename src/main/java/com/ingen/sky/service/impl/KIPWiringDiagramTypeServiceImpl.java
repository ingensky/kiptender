package com.ingen.sky.service.impl;

import com.ingen.sky.service.KIPWiringDiagramTypeService;
import com.ingen.sky.domain.KIPWiringDiagramType;
import com.ingen.sky.repository.KIPWiringDiagramTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link KIPWiringDiagramType}.
 */
@Service
@Transactional
public class KIPWiringDiagramTypeServiceImpl implements KIPWiringDiagramTypeService {

    private final Logger log = LoggerFactory.getLogger(KIPWiringDiagramTypeServiceImpl.class);

    private final KIPWiringDiagramTypeRepository kIPWiringDiagramTypeRepository;

    public KIPWiringDiagramTypeServiceImpl(KIPWiringDiagramTypeRepository kIPWiringDiagramTypeRepository) {
        this.kIPWiringDiagramTypeRepository = kIPWiringDiagramTypeRepository;
    }

    /**
     * Save a kIPWiringDiagramType.
     *
     * @param kIPWiringDiagramType the entity to save.
     * @return the persisted entity.
     */
    @Override
    public KIPWiringDiagramType save(KIPWiringDiagramType kIPWiringDiagramType) {
        log.debug("Request to save KIPWiringDiagramType : {}", kIPWiringDiagramType);
        return kIPWiringDiagramTypeRepository.save(kIPWiringDiagramType);
    }

    /**
     * Get all the kIPWiringDiagramTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<KIPWiringDiagramType> findAll(Pageable pageable) {
        log.debug("Request to get all KIPWiringDiagramTypes");
        return kIPWiringDiagramTypeRepository.findAll(pageable);
    }


    /**
     * Get one kIPWiringDiagramType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<KIPWiringDiagramType> findOne(Long id) {
        log.debug("Request to get KIPWiringDiagramType : {}", id);
        return kIPWiringDiagramTypeRepository.findById(id);
    }

    /**
     * Delete the kIPWiringDiagramType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete KIPWiringDiagramType : {}", id);

        kIPWiringDiagramTypeRepository.deleteById(id);
    }
}
