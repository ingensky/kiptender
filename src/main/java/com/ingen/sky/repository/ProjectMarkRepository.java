package com.ingen.sky.repository;

import com.ingen.sky.domain.ProjectMark;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ProjectMark entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectMarkRepository extends JpaRepository<ProjectMark, Long> {
}
