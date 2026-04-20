package com.college.taskmanagement.service;

import com.college.taskmanagement.model.TaskItem;
import com.college.taskmanagement.model.TaskStatus;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final EmployeeService employeeService;
    private final TaskService taskService;

    public DashboardService(EmployeeService employeeService, TaskService taskService) {
        this.employeeService = employeeService;
        this.taskService = taskService;
    }

    public DashboardSummary getSummary() {
        return new DashboardSummary(
                employeeService.count(),
                taskService.count(),
                taskService.countByStatus(TaskStatus.PENDING),
                taskService.countByStatus(TaskStatus.IN_PROGRESS),
                taskService.countByStatus(TaskStatus.COMPLETED),
                taskService.countOverdue(),
                taskService.upcomingTasks()
        );
    }
}
