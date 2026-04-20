package com.college.taskmanagement.config;

import com.college.taskmanagement.model.Employee;
import com.college.taskmanagement.service.EmployeeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {

    private final EmployeeService employeeService;

    public StringToEmployeeConverter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }
        return employeeService.findById(Long.valueOf(source));
    }
}
