package io.mann.ppmtool.exceptions.handler;

import io.mann.ppmtool.exceptions.*;
import io.mann.ppmtool.exceptions.response.ProjectIdExceptionResponse;
import io.mann.ppmtool.exceptions.response.ProjectNotFoundExceptionResponse;
import io.mann.ppmtool.exceptions.response.UsernameAlreadyExistsResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @Author: mann
 * @Date: 2019-02-27 9:28
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest request) {
        ProjectIdExceptionResponse exceptionResponse = new ProjectIdExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request) {
        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExists(UsernameAlreadyExistsException ex, WebRequest request) {
        UsernameAlreadyExistsResponse exceptionResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}