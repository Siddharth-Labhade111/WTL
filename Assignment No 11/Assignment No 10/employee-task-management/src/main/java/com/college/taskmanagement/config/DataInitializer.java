package com.college.taskmanagement.config;

import com.college.taskmanagement.model.Employee;
import com.college.taskmanagement.model.TaskCategory;
import com.college.taskmanagement.model.TaskItem;
import com.college.taskmanagement.model.TaskPriority;
import com.college.taskmanagement.model.TaskStatus;
import com.college.taskmanagement.repository.EmployeeRepository;
import com.college.taskmanagement.repository.TaskRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner loadSampleData(EmployeeRepository employeeRepository, TaskRepository taskRepository) {
        return args -> {
            if (employeeRepository.count() > 0 || taskRepository.count() > 0) {
                return;
            }

            Employee employeeOne = new Employee();
            employeeOne.setName("Dr. Anjali Mehta");
            employeeOne.setEmail("anjali.mehta@college.edu");
            employeeOne.setDesignation("Associate Professor");
            employeeOne.setDepartment("Engineering");
            employeeOne.setPhoneNumber("9876543210");

            Employee employeeTwo = new Employee();
            employeeTwo.setName("Prof. Rohan Kulkarni");
            employeeTwo.setEmail("rohan.kulkarni@college.edu");
            employeeTwo.setDesignation("Assistant Professor");
            employeeTwo.setDepartment("Engineering");
            employeeTwo.setPhoneNumber("9876501234");

            Employee employeeThree = new Employee();
            employeeThree.setName("Ms. Sneha Patil");
            employeeThree.setEmail("sneha.patil@college.edu");
            employeeThree.setDesignation("Lecturer");
            employeeThree.setDepartment("Engineering");
            employeeThree.setPhoneNumber("9988776655");

            employeeRepository.save(employeeOne);
            employeeRepository.save(employeeTwo);
            employeeRepository.save(employeeThree);

            taskRepository.save(createTask(
                    "Mid-Sem Exam Invigilation",
                    "Handle classroom invigilation for the second year WTL exam.",
                    TaskCategory.EXAM_DUTY,
                    TaskPriority.HIGH,
                    TaskStatus.PENDING,
                    LocalDate.now().plusDays(2),
                    employeeOne
            ));

            taskRepository.save(createTask(
                    "Project Guide Allocation",
                    "Assign faculty guides to final year project groups and share the final list.",
                    TaskCategory.PROJECT_GUIDE,
                    TaskPriority.HIGH,
                    TaskStatus.IN_PROGRESS,
                    LocalDate.now().plusDays(5),
                    employeeTwo
            ));

            taskRepository.save(createTask(
                    "NBA Documentation Update",
                    "Prepare course outcome mapping evidence for the department audit file.",
                    TaskCategory.DOCUMENTATION,
                    TaskPriority.MEDIUM,
                    TaskStatus.PENDING,
                    LocalDate.now().plusDays(7),
                    employeeThree
            ));

            taskRepository.save(createTask(
                    "Tech Fest Coordination Report",
                    "Compile the consolidated report for the annual technical event.",
                    TaskCategory.EVENT_COORDINATION,
                    TaskPriority.MEDIUM,
                    TaskStatus.COMPLETED,
                    LocalDate.now().plusDays(1),
                    employeeOne
            ));
        };
    }

    private TaskItem createTask(String title,
                                String description,
                                TaskCategory category,
                                TaskPriority priority,
                                TaskStatus status,
                                LocalDate dueDate,
                                Employee employee) {
        TaskItem taskItem = new TaskItem();
        taskItem.setTitle(title);
        taskItem.setDescription(description);
        taskItem.setCategory(category);
        taskItem.setPriority(priority);
        taskItem.setStatus(status);
        taskItem.setDueDate(dueDate);
        taskItem.setEmployee(employee);
        return taskItem;
    }
}
