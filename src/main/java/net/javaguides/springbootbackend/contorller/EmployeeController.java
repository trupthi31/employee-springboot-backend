package net.javaguides.springbootbackend.contorller;

import lombok.AllArgsConstructor;
import net.javaguides.springbootbackend.dto.EmployeeDto;
import net.javaguides.springbootbackend.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;
        // build Add Employee RESt APIs
    @PostMapping
        public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
            EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
    // build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int employeeId){
        EmployeeDto emlopyeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(emlopyeeDto);
    }

    //build get all employee rest api
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //build update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") int employeeId,
                                                      @RequestBody EmployeeDto updatedEmployee){
        EmployeeDto employeeDto = employeeService.upadteEmployee(employeeId,updatedEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    //build Delete employee rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted Succesfully");
    }
}
