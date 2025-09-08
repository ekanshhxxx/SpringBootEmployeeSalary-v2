package com.chitkara.payroll_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_salary_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSalaryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store employee ID instead of entity reference
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "salary_component_id", nullable = false)
    private SalaryComponent salaryComponent;

    private Double amount;
}
