package io.mann.ppmtool.repositories;

import io.mann.ppmtool.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Mann
 * @Date: 2019-05-21 20:36
 */
@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {
}
