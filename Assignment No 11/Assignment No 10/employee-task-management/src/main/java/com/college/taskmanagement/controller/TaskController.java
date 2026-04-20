package com.college.taskmanagement.controller;

import com.college.taskmanagement.model.TaskCategory;
import com.college.taskmanagement.model.TaskItem;
import com.college.taskmanagement.model.TaskPriority;
import com.college.taskmanagement.model.TaskStatus;
import com.college.taskmanagement.service.EmployeeService;
import com.college.taskmanagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    @ModelAttribute("categories")
    public TaskCategory[] categories() {
        return TaskCategory.values();
    }

    @ModelAttribute("priorities")
    public TaskPriority[] priorities() {
        return TaskPriority.values();
    }

    @ModelAttribute("statuses")
    public TaskStatus[] statuses() {
        return TaskStatus.values();
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String showTaskForm(Model model) {
        model.addAttribute("taskItem", new TaskItem());
        model.addAttribute("employees", employeeService.findAll());
        return "tasks/form";
    }

    @PostMapping
    public String saveTask(@Valid @ModelAttribute("taskItem") TaskItem taskItem,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employees", employeeService.findAll());
            return "tasks/form";
        }
        taskService.save(taskItem);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/status")
    public String updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        taskService.updateStatus(id, status);
        return "redirect:/tasks";
    }
}
