package com.chitkara.payroll_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "salary_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;    // e.g., Basic, HRA, Allowance
    private Double amount;
}
