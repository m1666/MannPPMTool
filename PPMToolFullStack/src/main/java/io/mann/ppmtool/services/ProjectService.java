package io.mann.ppmtool.services;

import io.mann.ppmtool.domain.Project;
import io.mann.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: mann
 * @Date: 2019-02-16 8:20
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {

        return projectRepository.save(project);
    }
}
