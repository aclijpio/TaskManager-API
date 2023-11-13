package ru.pio.aclij.taskmanagerapi.taskManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pio.aclij.taskmanagerapi.security.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
     List<Task> findByUser(User user);
     Optional<Task> findByUserAndId(User user, Long id);
     Optional<Task> deleteTaskByUserAndId(User user, Long id);
     List<Task> findByEndDateBetween(LocalDate endDate, LocalDate endDate2);
     List<Task> findByEndDate(LocalDate endDate);

}
