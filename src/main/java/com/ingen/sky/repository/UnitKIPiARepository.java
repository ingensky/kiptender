package com.ingen.sky.repository;

import com.ingen.sky.domain.UnitKIPiA;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the UnitKIPiA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitKIPiARepository extends JpaRepository<UnitKIPiA, Long> {
}
