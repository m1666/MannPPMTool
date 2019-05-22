package io.mann.ppmtool.services;

import io.mann.ppmtool.domain.Backlog;
import io.mann.ppmtool.domain.ProjectTask;
import io.mann.ppmtool.repositories.BacklogRepository;
import io.mann.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Mann
 * @Date: 2019-05-22 13:32
 */
@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // Exceptions: Project not found

        // PTs to be added to a specific project, project != null, Backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        // set the backlog to projectTask
        projectTask.setBacklog(backlog);

        // we want our project sequence to be like this: IDPRO-1 IDPRO-2 ...100 101...
        Integer BacklogSequence = backlog.getPTSequence();

        // Update the Backlog Sequence
        BacklogSequence++;
        backlog.setPTSequence(BacklogSequence);

        // Add Sequence to Project Task
        projectTask.setProjectSequence(projectIdentifier + "-" + BacklogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // INITIAL status when status is null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        // INITIAL priority when priority
        if (projectTask.getPriority() == null || projectTask.getPriority() == 0) {
            // In the future we need projectTask.getPriority() == 0 to handle the form
            projectTask.setPriority(3);
        }

        return projectTaskRepository.save(projectTask);
    }
}
