package ru.pio.aclij.taskmanagerapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.pio.aclij.taskmanagerapi.security.entities.Role;
import ru.pio.aclij.taskmanagerapi.security.entities.User;
import ru.pio.aclij.taskmanagerapi.security.service.UserService;
import ru.pio.aclij.taskmanagerapi.taskManager.Task;
import ru.pio.aclij.taskmanagerapi.taskManager.TaskRepository;
import ru.pio.aclij.taskmanagerapi.taskManager.TaskService;
import ru.pio.aclij.taskmanagerapi.taskManager.dtos.TaskDto;
import ru.pio.aclij.taskmanagerapi.taskManager.excetpions.crud.TaskNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TaskService taskService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "user",
                        null,
                        Set.of(new SimpleGrantedAuthority("USER_ROLE"))
                )
        );
        Role role = new Role();
        role.setName("USER_ROLE");
        Set<Role> roles = Set.of(role);
        User fakeUser = new User("user", "100", "email", roles);
        when(taskService.getCurrentUser()).thenReturn(fakeUser);


    }

    @Test
    void testGetTaskById() {
        Long taskId = 1L;
        Task task = new Task();
        when(repository.findById(taskId)).thenReturn(Optional.of(task));

        assertEquals(task, taskService.getTaskById(taskId));
    }

    @Test
    void testGetTaskByIdSer() {
        Long taskId = 1L;
        when(repository.findById(taskId)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> taskService.getTaskById(taskId));
    }

    @Test
    void testGetAllTasks() {
        User user = new User();
        List<Task> tasks = Arrays.asList(new Task(), new Task());
        when(taskService.getCurrentUser()).thenReturn(user);
        when(repository.findByUser(user)).thenReturn(tasks);

        ResponseEntity<?> response = taskService.getAllTasks();

        assertEquals(ResponseEntity.ok(tasks), response);
    }


    @Test
    void testDelete() {
        Long taskId = 1L;
        User user = new User();
        when(taskService.getCurrentUser()).thenReturn(user);

        taskService.delete(taskId);

        verify(repository).deleteTaskByUserAndId(user, taskId);
    }

    @Test
    void testUpdate() {
        Long taskId = 1L;
        TaskDto taskDto = new TaskDto();
        User user = new User();
        Task existingTask = new Task();
        when(taskService.getCurrentUser()).thenReturn(user);
        when(repository.findByUserAndId(user, taskId)).thenReturn(existingTask);

        ResponseEntity<Task> response = taskService.update(taskId, taskDto);

        assertEquals(ResponseEntity.ok(existingTask), response);
        verify(repository).save(existingTask);
    }

    @Test
    void testCreate() {
        TaskDto taskDto = new TaskDto();
        User user = new User();
        when(taskService.getCurrentUser()).thenReturn(user);

        ResponseEntity<Task> response = taskService.create(taskDto);

        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.ok(response.getBody()), response);
        verify(repository).save(any(Task.class));
    }

    @Test
    void taskCompletion_shouldToggleTaskCompletion() {
        Long taskId = 1L;
        boolean toggle = true;
        User user = new User();
        Task task = new Task();
        when(taskService.getCurrentUser()).thenReturn(user);
        when(repository.findByUserAndId(user, taskId)).thenReturn(task);

        ResponseEntity<Task> response = taskService.taskCompletion(taskId, toggle);

        assertEquals(ResponseEntity.ok(task), response);
        verify(repository).save(task);
    }
}