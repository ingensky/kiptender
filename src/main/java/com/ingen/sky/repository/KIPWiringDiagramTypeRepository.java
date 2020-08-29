package com.ingen.sky.repository;

import com.ingen.sky.domain.KIPWiringDiagramType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the KIPWiringDiagramType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KIPWiringDiagramTypeRepository extends JpaRepository<KIPWiringDiagramType, Long> {
}
