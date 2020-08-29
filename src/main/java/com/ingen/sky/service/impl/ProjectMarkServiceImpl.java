package com.ingen.sky.service.impl;

import com.ingen.sky.service.ProjectMarkService;
import com.ingen.sky.domain.ProjectMark;
import com.ingen.sky.repository.ProjectMarkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ProjectMark}.
 */
@Service
@Transactional
public class ProjectMarkServiceImpl implements ProjectMarkService {

    private final Logger log = LoggerFactory.getLogger(ProjectMarkServiceImpl.class);

    private final ProjectMarkRepository projectMarkRepository;

    public ProjectMarkServiceImpl(ProjectMarkRepository projectMarkRepository) {
        this.projectMarkRepository = projectMarkRepository;
    }

    /**
     * Save a projectMark.
     *
     * @param projectMark the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ProjectMark save(ProjectMark projectMark) {
        log.debug("Request to save ProjectMark : {}", projectMark);
        return projectMarkRepository.save(projectMark);
    }

    /**
     * Get all the projectMarks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectMark> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectMarks");
        return projectMarkRepository.findAll(pageable);
    }


    /**
     * Get one projectMark by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectMark> findOne(Long id) {
        log.debug("Request to get ProjectMark : {}", id);
        return projectMarkRepository.findById(id);
    }

    /**
     * Delete the projectMark by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectMark : {}", id);

        projectMarkRepository.deleteById(id);
    }
}
