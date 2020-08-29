package com.ingen.sky.repository;

import com.ingen.sky.domain.InstallationKIPiA;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InstallationKIPiA entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InstallationKIPiARepository extends JpaRepository<InstallationKIPiA, Long> {
}
