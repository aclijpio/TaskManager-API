package ru.pio.aclij.taskmanagerapi.taskManager;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ru.pio.aclij.taskmanagerapi.security.entities.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Data
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    @NotBlank(message = "Заголовки задач не могут быть пустыми.")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate endDate;


    public Task(String title, String description, Boolean completed, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.endDate = endDate;
    }

    public Task() {

    }

    public Task(User user, String title, String description, Boolean completed, LocalDate endDate) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.endDate = endDate;
    }
}
