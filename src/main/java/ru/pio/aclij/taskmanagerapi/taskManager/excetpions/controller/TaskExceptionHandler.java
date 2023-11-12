package ru.pio.aclij.taskmanagerapi.taskManager.excetpions.controller;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskNotFoundException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskCollectionNotFoundException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.TaskException;

@ControllerAdvice
@Log
public class TaskExceptionHandler {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity<String> handleTaskException(TaskNotFoundException e){
        String message = e.getMessage();
        log.info(String.format("Message: %s. Status : %s", message, HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TaskCollectionNotFoundException.class)
    public ResponseEntity<String> handleFailedFindTasks(TaskCollectionNotFoundException e){
        String message = e.getMessage();
        log.info(String.format("Message: %s. Status : %s", message, HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }


}
