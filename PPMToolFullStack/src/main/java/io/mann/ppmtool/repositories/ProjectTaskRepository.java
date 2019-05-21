package io.mann.ppmtool.repositories;

import io.mann.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mann
 * @Date: 2019-05-21 20:39
 */
@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {
}
