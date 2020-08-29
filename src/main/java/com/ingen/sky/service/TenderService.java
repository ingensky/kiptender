package com.ingen.sky.service;

import com.ingen.sky.domain.Tender;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Tender}.
 */
public interface TenderService {

    /**
     * Save a tender.
     *
     * @param tender the entity to save.
     * @return the persisted entity.
     */
    Tender save(Tender tender);

    /**
     * Get all the tenders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Tender> findAll(Pageable pageable);

    /**
     * Get all the tenders with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<Tender> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" tender.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Tender> findOne(Long id);

    /**
     * Delete the "id" tender.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
