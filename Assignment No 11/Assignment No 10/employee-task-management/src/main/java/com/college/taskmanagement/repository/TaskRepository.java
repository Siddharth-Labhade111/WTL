package com.college.taskmanagement.repository;

import com.college.taskmanagement.model.TaskItem;
import com.college.taskmanagement.model.TaskStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskItem, Long> {

    long countByStatus(TaskStatus status);

    long countByStatusNotAndDueDateBefore(TaskStatus status, LocalDate dueDate);

    List<TaskItem> findTop5ByOrderByDueDateAsc();

    List<TaskItem> findAllByOrderByDueDateAsc();
}
