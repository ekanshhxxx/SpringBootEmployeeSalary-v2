package com.chitkara.payroll_service.repository;

import com.chitkara.payroll_service.entity.EmployeeSalaryComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeSalaryComponentRepository extends JpaRepository<EmployeeSalaryComponent, Long> {
    List<EmployeeSalaryComponent> findByEmployeeId(Long employeeId);
}
