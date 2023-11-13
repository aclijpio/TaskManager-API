package ru.pio.aclij.taskmanagerapi.taskManager.dtos;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class TaskDto {
    private String title;
    private String description;
    private boolean toggle;
    private LocalDate endDate;
}
