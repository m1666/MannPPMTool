package io.mann.ppmtool.web;

import io.mann.ppmtool.domain.ProjectTask;
import io.mann.ppmtool.services.MapValidationErrorService;
import io.mann.ppmtool.services.ProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author: Mann
 * @Date: 2019-05-22 13:55
 */
@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addPTtoBacklog(@Valid @RequestBody ProjectTask projectTask,
                                            BindingResult result, @PathVariable String backlog_id) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        ProjectTask addProjectTask = projectTaskService.addProjectTask(backlog_id, projectTask);
        return new ResponseEntity<ProjectTask>(addProjectTask, HttpStatus.CREATED);
    }

    @GetMapping("/{backlog_id}")
    public Iterable<ProjectTask> getProjectBacklog(@PathVariable String backlog_id) {

        return projectTaskService.findBacklogById(backlog_id);
    }

    @GetMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id) {
        ProjectTask projectTask = projectTaskService.findPTByProjectSequence(backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(projectTask, HttpStatus.OK);
    }

    @PatchMapping("/{backlog_id}/{pt_id}")
    public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask projectTask, BindingResult result,
                                               @PathVariable String backlog_id, @PathVariable String pt_id) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        ProjectTask updateProjectTask = projectTaskService.updateByProjectSequence(projectTask, backlog_id, pt_id);

        return new ResponseEntity<ProjectTask>(updateProjectTask, HttpStatus.OK);
    }
}
