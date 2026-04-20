package com.college.taskmanagement.service;

import com.college.taskmanagement.model.TaskItem;
import java.util.List;

public record DashboardSummary(
        long employeeCount,
        long taskCount,
        long pendingTasks,
        long inProgressTasks,
        long completedTasks,
        long overdueTasks,
        List<TaskItem> upcomingTasks
) {
}