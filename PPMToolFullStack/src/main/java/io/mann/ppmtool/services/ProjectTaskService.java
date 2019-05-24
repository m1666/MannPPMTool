package io.mann.ppmtool.services;

import io.mann.ppmtool.domain.Backlog;
import io.mann.ppmtool.domain.Project;
import io.mann.ppmtool.domain.ProjectTask;
import io.mann.ppmtool.exceptions.ProjectNotFoundException;
import io.mann.ppmtool.repositories.BacklogRepository;
import io.mann.ppmtool.repositories.ProjectRepository;
import io.mann.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // Exceptions: Project not found

        /**
         * {
         *     ProjectNotFound: "Project not found"
         * }
         */
        try {
            // PTs to be added to a specific project, project != null, Backlog exists
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            // set the backlog to projectTask
            projectTask.setBacklog(backlog);
            //we want our project sequence to be like this: IDPRO-1  IDPRO-2  ...100 101
            Integer BacklogSequence = backlog.getPTSequence();
            // Update the BL SEQUENCE
            BacklogSequence++;

            backlog.setPTSequence(BacklogSequence);
            backlogRepository.save(backlog);

            //Add Sequence to Project Task
            projectTask.setProjectSequence(backlog.getProjectIdentifier() + "-" + BacklogSequence);
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
        } catch (Exception e) {
            throw new ProjectNotFoundException("Project not Found");
        }

    }

    public Iterable<ProjectTask> findBacklogById(String id) {

        Project project = projectRepository.findByProjectIdentifier(id);
        if (project == null) {
            throw new ProjectNotFoundException("Project with ID: '" + id + "' does not exist");
        }
        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id, String pt_id) {
        // make sure we are searching on the right backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
        if (backlog == null) {
            throw new ProjectNotFoundException("Project with ID: '" + backlog_id + "' does not exist");
        }

        // make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);

        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' not found");
        }

        // make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().equals(backlog_id)) {
            throw new ProjectNotFoundException("Project Task '" + pt_id + "' does not exist in project: '" + backlog_id + "'");
        }

        return projectTask;
    }

    public ProjectTask updateByProjectSequence(ProjectTask updateTask, String backlog_id, String pt_id) {
        // Update Project task
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);

        projectTask = updateTask;

        return projectTaskRepository.save(projectTask);
    }

    public void deletePTByProjectSequence(String backlog_id, String pt_id){
        ProjectTask projectTask = findPTByProjectSequence(backlog_id, pt_id);
        projectTaskRepository.delete(projectTask);
    }

}
