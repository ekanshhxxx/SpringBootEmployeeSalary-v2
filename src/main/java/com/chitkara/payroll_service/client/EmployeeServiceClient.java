package com.chitkara.payroll_service.client;

import com.chitkara.payroll_service.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EmployeeServiceClient {

    @Autowired
    @Qualifier("employeeWebClient") // Use the renamed bean
    private WebClient employeeWebClient;

    public EmployeeDto getEmployeeById(Long employeeId) {
        return employeeWebClient
                .get()
                .uri("/api/employees/{id}", employeeId)
                .retrieve()
                .bodyToMono(EmployeeDto.class)
                .block();
    }
}
