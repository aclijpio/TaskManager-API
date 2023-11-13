package ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud;

import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.TaskException;

public class TaskCreationException extends TaskException {

    public TaskCreationException() {
    }

    public TaskCreationException(String message) {
        super(message);
    }

    public TaskCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskCreationException(Throwable cause) {
        super(cause);
    }

    public TaskCreationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
