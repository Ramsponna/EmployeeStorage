package com.test.controllers;

import com.test.dto.EmployeeDetailsDto;
import com.test.dto.EmployeeInsertDto;
import com.test.dto.EmployeeList;
import com.test.dto.EmployeeUpdateDto;
import com.test.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping(value = "/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    public final EmployeeService employeeService;

    /**
     * Method to add employees for a company
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDetailsDto> addEmployee(@RequestBody EmployeeInsertDto employeeInsertDto) {
        return ResponseEntity.ok(new EmployeeDetailsDto(employeeService.createEmployee(employeeInsertDto.getEmployee())));
    }

    /**
     * Method to get all employees for a company
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeList> getAllEmployees(@RequestParam @NotEmpty(message = "Valid company Id is mandatory") long companyId) {
        return ResponseEntity.ok(new EmployeeList(employeeService.findByCompanyId(companyId)));
    }

    /**
     * Method to get employee details by id
     */
    @GetMapping(value = "/{employeeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDetailsDto> getEmployeeById(@PathVariable long employeeId) {
        return ResponseEntity.ok(new EmployeeDetailsDto(employeeService.findByEmployeeId(employeeId)));
    }

    /**
     * Method to update employee details
     */
    @PutMapping(value = "/{employeeId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDetailsDto> updateEmployeeData(@PathVariable long employeeId,
                                                                 @RequestBody @Valid EmployeeUpdateDto employeeUpdateDto) {
        return ResponseEntity.ok(new EmployeeDetailsDto(employeeService.update(employeeId, employeeUpdateDto.getEmployee())));
    }

    /**
     * Method to delete a employee record
     */
    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long employeeId) {
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }
}

