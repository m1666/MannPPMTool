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

    /**
     * 根据项目编号查询
     * @param Identifier 项目编号
     * @return Backlog
     */
    Backlog findByProjectIdentifier(String Identifier);
}
