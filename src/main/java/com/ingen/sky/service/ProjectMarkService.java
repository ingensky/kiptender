package com.ingen.sky.service;

import com.ingen.sky.domain.ProjectMark;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link ProjectMark}.
 */
public interface ProjectMarkService {

    /**
     * Save a projectMark.
     *
     * @param projectMark the entity to save.
     * @return the persisted entity.
     */
    ProjectMark save(ProjectMark projectMark);

    /**
     * Get all the projectMarks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectMark> findAll(Pageable pageable);


    /**
     * Get the "id" projectMark.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectMark> findOne(Long id);

    /**
     * Delete the "id" projectMark.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
