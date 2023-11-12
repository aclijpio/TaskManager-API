package ru.pio.aclij.taskmanagerapi.taskManager;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TasksService service;
    @GetMapping("")
    public ResponseEntity<?> index(){
        return ResponseEntity.ok(service.getAllTasks());
    }
    @PostMapping("/create")
    public void create(@RequestBody Task task){
        service.create(task);
    }
    @PostMapping("/update/{id}")
    public void update(@RequestBody Task task){
        service.update(task);
    }
    @PostMapping("/toggle/{id}")
    public void label(@PathVariable("id") int id){
        service.taskCompletion(id);
    }
    @PostMapping("/delete/{id}")
    public void delete(@PathVariable("id") int id){
        service.delete(id);
    }
}
