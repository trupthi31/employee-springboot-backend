package net.javaguides.springbootbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springbootbackend.dto.EmployeeDto;
import net.javaguides.springbootbackend.entity.Employee;
import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.mapper.EmployeeMapper;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // to create spring bean for this class
@AllArgsConstructor

public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
       Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> //employee object
                        new ResourceNotFoundException("Employee is not exist with given id: " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) ->
                EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto upadteEmployee(int employeeId, EmployeeDto upadteEmployee) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
              new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));

       employee.setFirstName(upadteEmployee.getFirstName());
       employee.setLastName(upadteEmployee.getLastName());
       employee.setEmail(upadteEmployee.getEmail());
       Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee is not exists with given id:" + employeeId));

        employeeRepository.deleteById(employeeId);
    }


}
