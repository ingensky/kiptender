package com.ingen.sky.service.impl;

import com.ingen.sky.service.TenderService;
import com.ingen.sky.domain.Tender;
import com.ingen.sky.repository.TenderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tender}.
 */
@Service
@Transactional
public class TenderServiceImpl implements TenderService {

    private final Logger log = LoggerFactory.getLogger(TenderServiceImpl.class);

    private final TenderRepository tenderRepository;

    public TenderServiceImpl(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    /**
     * Save a tender.
     *
     * @param tender the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Tender save(Tender tender) {
        log.debug("Request to save Tender : {}", tender);
        return tenderRepository.save(tender);
    }

    /**
     * Get all the tenders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Tender> findAll(Pageable pageable) {
        log.debug("Request to get all Tenders");
        return tenderRepository.findAll(pageable);
    }


    /**
     * Get all the tenders with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<Tender> findAllWithEagerRelationships(Pageable pageable) {
        return tenderRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     * Get one tender by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Tender> findOne(Long id) {
        log.debug("Request to get Tender : {}", id);
        return tenderRepository.findOneWithEagerRelationships(id);
    }

    /**
     * Delete the tender by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tender : {}", id);

        tenderRepository.deleteById(id);
    }
}
