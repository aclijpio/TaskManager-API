package ru.pio.aclij.taskmanagerapi.taskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskCollectionNotFoundException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskDeletionException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskNotFoundException;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TaskRepository repository;
    public Task getTaskById(int id){
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task not found with id : %s", id)));
    }
    public List<Task> getAllTasks()
    {
        List<Task> tasks = repository.findAll();
        if (tasks.isEmpty())
            throw new TaskCollectionNotFoundException("Failed to find task collection");
        return tasks;
    }
    public void delete(int id){
        repository.deleteById(id);
    }
    public void update(Task task){
        repository.save(task);
    }
    public void create(Task task){
        repository.save(task);
    }
    public void taskCompletion(int id) {
        Task task = getTaskById(id);
        task.setCompleted(!task.getCompleted());
        repository.save(task);
    }
}
