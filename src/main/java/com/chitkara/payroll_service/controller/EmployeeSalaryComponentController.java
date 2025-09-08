package com.chitkara.payroll_service.controller;

import com.chitkara.payroll_service.entity.EmployeeSalaryComponent;
import com.chitkara.payroll_service.repository.EmployeeSalaryComponentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-salary-components")
public class EmployeeSalaryComponentController {

    private final EmployeeSalaryComponentRepository repository;

    public EmployeeSalaryComponentController(EmployeeSalaryComponentRepository repository) {
        this.repository = repository;
    }

    // Add a salary component for an employee
    @PostMapping
    public EmployeeSalaryComponent add(@RequestBody EmployeeSalaryComponent esc) {
        return repository.save(esc);
    }

    // Get all salary components for all employees
    @GetMapping
    public List<EmployeeSalaryComponent> getAll() {
        return repository.findAll();
    }

    // Optionally: Get components for a single employee
    @GetMapping("/employee/{empId}")
    public List<EmployeeSalaryComponent> getByEmployee(@PathVariable Long empId) {
        return repository.findByEmployeeId(empId);
    }
}
