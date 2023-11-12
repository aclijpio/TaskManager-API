package ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud;

import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.TaskException;

public class TaskDeletionException extends TaskException {
    public TaskDeletionException() {
    }

    public TaskDeletionException(String message) {
        super(message);
    }

    public TaskDeletionException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskDeletionException(Throwable cause) {
        super(cause);
    }

    public TaskDeletionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
