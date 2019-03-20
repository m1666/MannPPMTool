package io.mann.ppmtool.repositories;

        import io.mann.ppmtool.domain.Project;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.stereotype.Repository;

/**
 * @Author: mann
 * @Date: 2019-02-10 21:23
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);

    Project findByProjectIdentifier(String projectId);
}


