package com.chitkara.payroll_service.service;

import com.chitkara.payroll_service.client.EmployeeServiceClient;
import com.chitkara.payroll_service.dto.EmployeeDto;
import com.chitkara.payroll_service.entity.PayrollRun;
import com.chitkara.payroll_service.entity.EmployeeSalaryComponent;
import com.chitkara.payroll_service.repository.PayrollRunRepository;
import com.chitkara.payroll_service.repository.EmployeeSalaryComponentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PayrollService {

    private final PayrollRunRepository payrollRunRepository;
    private final EmployeeSalaryComponentRepository empSalaryCompRepo;
    private final EmployeeServiceClient employeeServiceClient;

    public PayrollService(PayrollRunRepository payrollRunRepository,
                          EmployeeSalaryComponentRepository empSalaryCompRepo,
                          EmployeeServiceClient employeeServiceClient) {
        this.payrollRunRepository = payrollRunRepository;
        this.empSalaryCompRepo = empSalaryCompRepo;
        this.employeeServiceClient = employeeServiceClient;
    }

    public PayrollRun generatePayroll(Long employeeId, LocalDate month) {
        // Get employee details from Employee Service via REST call
        EmployeeDto employee = employeeServiceClient.getEmployeeById(employeeId);

        if (employee == null) {
            throw new RuntimeException("Employee not found with id: " + employeeId);
        }

        // Get salary components for this employee
        List<EmployeeSalaryComponent> components = empSalaryCompRepo.findByEmployeeId(employeeId);

        double totalSalary = components.stream()
                .mapToDouble(EmployeeSalaryComponent::getAmount)
                .sum();

        String breakdownJson = components.stream()
                .map(c -> "\"" + c.getSalaryComponent().getName() + "\":" + c.getAmount())
                .reduce("{", (a, b) -> a.equals("{") ? a + b : a + "," + b) + "}";

        PayrollRun payroll = PayrollRun.builder()
                .employeeId(employee.getId())
                .employeeName(employee.getName())
                .month(month)
                .totalSalary(totalSalary)
                .salaryBreakdown(breakdownJson)
                .build();

        return payrollRunRepository.save(payroll);
    }

    public List<PayrollRun> getPayrollByEmployee(Long employeeId) {
        return payrollRunRepository.findByEmployeeId(employeeId);
    }

    public List<PayrollRun> getPayrollByMonth(LocalDate month) {
        return payrollRunRepository.findByMonth(month);
    }
}
