package com.college.taskmanagement.service;

import com.college.taskmanagement.model.TaskItem;
import com.college.taskmanagement.model.TaskStatus;
import com.college.taskmanagement.repository.TaskRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskItem> findAll() {
        return taskRepository.findAllByOrderByDueDateAsc();
    }

    public List<TaskItem> upcomingTasks() {
        return taskRepository.findTop5ByOrderByDueDateAsc();
    }

    public TaskItem save(TaskItem taskItem) {
        return taskRepository.save(taskItem);
    }

    public TaskItem findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    }

    public void updateStatus(Long id, TaskStatus status) {
        TaskItem taskItem = findById(id);
        taskItem.setStatus(status);
        taskRepository.save(taskItem);
    }

    public long count() {
        return taskRepository.count();
    }

    public long countByStatus(TaskStatus status) {
        return taskRepository.countByStatus(status);
    }

    public long countOverdue() {
        return taskRepository.countByStatusNotAndDueDateBefore(TaskStatus.COMPLETED, LocalDate.now());
    }
}
