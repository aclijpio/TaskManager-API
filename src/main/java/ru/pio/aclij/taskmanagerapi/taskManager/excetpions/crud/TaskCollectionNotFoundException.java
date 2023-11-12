package ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud;

import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.TaskException;

public class TaskCollectionNotFoundException extends TaskException {
    public TaskCollectionNotFoundException() {
    }

    public TaskCollectionNotFoundException(String message) {
        super(message);
    }

    public TaskCollectionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskCollectionNotFoundException(Throwable cause) {
        super(cause);
    }

    public TaskCollectionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
