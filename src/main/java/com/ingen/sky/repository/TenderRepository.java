package com.ingen.sky.repository;

import com.ingen.sky.domain.Tender;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Tender entity.
 */
@Repository
public interface TenderRepository extends JpaRepository<Tender, Long> {

    @Query(value = "select distinct tender from Tender tender left join fetch tender.projectMarks left join fetch tender.unitKIPiAGroups left join fetch tender.installationKIPiAGroups",
        countQuery = "select count(distinct tender) from Tender tender")
    Page<Tender> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct tender from Tender tender left join fetch tender.projectMarks left join fetch tender.unitKIPiAGroups left join fetch tender.installationKIPiAGroups")
    List<Tender> findAllWithEagerRelationships();

    @Query("select tender from Tender tender left join fetch tender.projectMarks left join fetch tender.unitKIPiAGroups left join fetch tender.installationKIPiAGroups where tender.id =:id")
    Optional<Tender> findOneWithEagerRelationships(@Param("id") Long id);
}
