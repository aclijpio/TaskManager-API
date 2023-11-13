package ru.pio.aclij.taskmanagerapi.taskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pio.aclij.taskmanagerapi.taskManager.dtos.TaskDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
@PreAuthorize("hasRole('ROLE_USER')")
public class TaskApiController {
    private final TaskService service;
    @GetMapping("/all")
    public ResponseEntity<?> getAllTask(){
        return service.getAllTasks();
    }
    @GetMapping("/forToday")
    public ResponseEntity<?> getTasksForToday(){
        return service.getTaskForDay();
    }
    @GetMapping("/forWeek")
    public ResponseEntity<?> getTasksForWeek(){
        return service.getTaskForWeek();
    }
    @GetMapping("/forMonth")
    public ResponseEntity<?> getTasksForMonth(){
        return service.getTaskForMonth();
    }
    @PostMapping("/create")
    public ResponseEntity<Task> create(@RequestBody TaskDto taskDto){
        return service.create(taskDto);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Task> update(@PathVariable("id") Long id, @RequestBody TaskDto taskDto){
        return service.update(id, taskDto);
    }
    @PatchMapping("/toggle/{id}")
    public ResponseEntity<Task> complete(@PathVariable("id") Long id, @RequestParam boolean toggle){
         return service.taskCompletion(id, toggle);
    }
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }
}
