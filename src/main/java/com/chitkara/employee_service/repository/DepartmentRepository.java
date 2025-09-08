package com.chitkara.employee_service.repository;

import com.chitkara.employee_service.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
