package net.javaguides.springbootbackend.service;

import net.javaguides.springbootbackend.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(int employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto upadteEmployee(int employeeId, EmployeeDto upadteEmployee);

    void deleteEmployee(int employeeId);
}
