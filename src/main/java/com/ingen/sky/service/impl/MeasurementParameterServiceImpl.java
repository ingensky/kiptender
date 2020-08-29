package com.ingen.sky.service.impl;

import com.ingen.sky.service.MeasurementParameterService;
import com.ingen.sky.domain.MeasurementParameter;
import com.ingen.sky.repository.MeasurementParameterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MeasurementParameter}.
 */
@Service
@Transactional
public class MeasurementParameterServiceImpl implements MeasurementParameterService {

    private final Logger log = LoggerFactory.getLogger(MeasurementParameterServiceImpl.class);

    private final MeasurementParameterRepository measurementParameterRepository;

    public MeasurementParameterServiceImpl(MeasurementParameterRepository measurementParameterRepository) {
        this.measurementParameterRepository = measurementParameterRepository;
    }

    /**
     * Save a measurementParameter.
     *
     * @param measurementParameter the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MeasurementParameter save(MeasurementParameter measurementParameter) {
        log.debug("Request to save MeasurementParameter : {}", measurementParameter);
        return measurementParameterRepository.save(measurementParameter);
    }

    /**
     * Get all the measurementParameters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MeasurementParameter> findAll(Pageable pageable) {
        log.debug("Request to get all MeasurementParameters");
        return measurementParameterRepository.findAll(pageable);
    }


    /**
     * Get one measurementParameter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MeasurementParameter> findOne(Long id) {
        log.debug("Request to get MeasurementParameter : {}", id);
        return measurementParameterRepository.findById(id);
    }

    /**
     * Delete the measurementParameter by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MeasurementParameter : {}", id);

        measurementParameterRepository.deleteById(id);
    }
}
