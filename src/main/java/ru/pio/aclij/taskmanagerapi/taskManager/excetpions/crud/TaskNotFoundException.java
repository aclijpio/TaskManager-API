package ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud;

import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.TaskException;

public class TaskNotFoundException extends TaskException {
    public TaskNotFoundException() {
    }

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(Throwable cause) {
        super(cause);
    }

    public TaskNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
