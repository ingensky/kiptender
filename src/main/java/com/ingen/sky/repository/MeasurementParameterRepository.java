package com.ingen.sky.repository;

import com.ingen.sky.domain.MeasurementParameter;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MeasurementParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MeasurementParameterRepository extends JpaRepository<MeasurementParameter, Long> {
}
