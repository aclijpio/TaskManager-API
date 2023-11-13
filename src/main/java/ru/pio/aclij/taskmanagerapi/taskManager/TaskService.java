package ru.pio.aclij.taskmanagerapi.taskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagerapi.security.entities.User;
import ru.pio.aclij.taskmanagerapi.security.service.UserService;
import ru.pio.aclij.taskmanagerapi.taskManager.dtos.TaskDto;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskCollectionNotFoundException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskDeletionException;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserService userService;
    public Task getTaskById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task not found with id : %s", id)));
    }
    public Task getTaskByUserAndId(User user, Long id){
        return repository.findByUserAndId(user, id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task not found with id : %s", id)));
    }
    public ResponseEntity<?> getAllTasks()
    {
        User user = getCurrentUser();
        List<Task> tasks = repository.findByUser(user);
        return buildResponse(tasks);
    }
    public ResponseEntity<?> getTaskForDay(){
        LocalDate start = LocalDate.now();
        LocalDate end = start.minusDays(1);
        List<Task> tasks = repository.findByEndDateBetween(start, end);
        return buildResponse(tasks);
    }
    public ResponseEntity<?> getTaskForWeek(){
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusWeeks(1);
        List<Task> tasks = repository.findByEndDateBetween(start, end);
        return buildResponse(tasks);
    }
    public ResponseEntity<?> getTaskForMonth(){
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusMonths(1);
        List<Task> tasks = repository.findByEndDateBetween(start, end);
        return buildResponse(tasks);
    }
    public void delete(Long id){
        repository.deleteTaskByUserAndId(getCurrentUser(), id)
                .orElseThrow(() -> new TaskDeletionException("Failed to delete task with id : " + id));
    }
    public ResponseEntity<Task> update(Long id, TaskDto taskDto){

        Task task = getTaskByUserAndId(getCurrentUser(), id);

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.isToggle());
        task.setEndDate(taskDto.getEndDate());

        repository.save(task);
        return ResponseEntity.ok(task);
    }
    public ResponseEntity<Task> create(TaskDto taskDto){
        User user = getCurrentUser();
        Task task = new Task(
                taskDto.getTitle(),
                taskDto.getDescription(),
                false,
                taskDto.getEndDate()
        );
        task.setUser(user);
        repository.save(task);
        return ResponseEntity.ok(task);
    }
    public ResponseEntity<Task> taskCompletion(Long id, boolean toggle) {
        Task task = getTaskByUserAndId(getCurrentUser(), id);
        task.setCompleted(toggle);

        repository.save(task);
        return ResponseEntity.ok(task);
    }
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }
        String username = authentication.getName();
        return userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with " + username));
    }
    private ResponseEntity<?> buildResponse(List<Task> tasks){
        if (tasks.isEmpty())
            return new ResponseEntity<>(new TaskCollectionNotFoundException(), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(tasks);
    }
}
