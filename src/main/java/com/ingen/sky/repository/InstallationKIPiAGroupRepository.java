package com.ingen.sky.repository;

import com.ingen.sky.domain.InstallationKIPiAGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the InstallationKIPiAGroup entity.
 */
@Repository
public interface InstallationKIPiAGroupRepository extends JpaRepository<InstallationKIPiAGroup, Long> {

    @Query(value = "select distinct installationKIPiAGroup from InstallationKIPiAGroup installationKIPiAGroup left join fetch installationKIPiAGroup.installationKIPiAS",
        countQuery = "select count(distinct installationKIPiAGroup) from InstallationKIPiAGroup installationKIPiAGroup")
    Page<InstallationKIPiAGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct installationKIPiAGroup from InstallationKIPiAGroup installationKIPiAGroup left join fetch installationKIPiAGroup.installationKIPiAS")
    List<InstallationKIPiAGroup> findAllWithEagerRelationships();

    @Query("select installationKIPiAGroup from InstallationKIPiAGroup installationKIPiAGroup left join fetch installationKIPiAGroup.installationKIPiAS where installationKIPiAGroup.id =:id")
    Optional<InstallationKIPiAGroup> findOneWithEagerRelationships(@Param("id") Long id);
}
