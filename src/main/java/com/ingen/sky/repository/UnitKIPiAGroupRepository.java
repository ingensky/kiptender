package com.ingen.sky.repository;

import com.ingen.sky.domain.UnitKIPiAGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the UnitKIPiAGroup entity.
 */
@Repository
public interface UnitKIPiAGroupRepository extends JpaRepository<UnitKIPiAGroup, Long> {

    @Query(value = "select distinct unitKIPiAGroup from UnitKIPiAGroup unitKIPiAGroup left join fetch unitKIPiAGroup.unitKIPiAS",
        countQuery = "select count(distinct unitKIPiAGroup) from UnitKIPiAGroup unitKIPiAGroup")
    Page<UnitKIPiAGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct unitKIPiAGroup from UnitKIPiAGroup unitKIPiAGroup left join fetch unitKIPiAGroup.unitKIPiAS")
    List<UnitKIPiAGroup> findAllWithEagerRelationships();

    @Query("select unitKIPiAGroup from UnitKIPiAGroup unitKIPiAGroup left join fetch unitKIPiAGroup.unitKIPiAS where unitKIPiAGroup.id =:id")
    Optional<UnitKIPiAGroup> findOneWithEagerRelationships(@Param("id") Long id);
}
