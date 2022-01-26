package com.test.service.impl;

import com.test.dto.EmployeeInsertInfo;
import com.test.dto.EmployeeLightResponseDto;
import com.test.dto.EmployeeResponseDto;
import com.test.dto.EmployeeUpdateInfo;
import com.test.entity.Company;
import com.test.entity.Employee;
import com.test.exception.CompanyNotFoundException;
import com.test.exception.EmployeeNotFoundException;
import com.test.repository.CompanyRepository;
import com.test.repository.EmployeeRepository;
import com.test.service.EmployeeService;
import com.test.service.converter.EmployeeBasicInfoConverter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private static final String COMPANY_NOT_FOUND = "Company not found";
    private static final String EMPLOYEE_NOT_FOUND = "Employee not found";

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public EmployeeResponseDto createEmployee(EmployeeInsertInfo employeeInsertInfo) {
        Employee employee = EmployeeBasicInfoConverter.asEmployeeModel(employeeInsertInfo);
        Company company = companyRepository.findById(employeeInsertInfo.getCompanyId())
                                           .orElseThrow(() -> new CompanyNotFoundException(
                                               COMPANY_NOT_FOUND + " " + employeeInsertInfo.getCompanyId()));
        employee.setCompany(company);
        return EmployeeBasicInfoConverter.asEmployeeResponseDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponseDto findByEmployeeId(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + " " + employeeId));
        return EmployeeBasicInfoConverter.asEmployeeResponseDto(employee);
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        return employeeRepository.findAll().stream().map(EmployeeBasicInfoConverter::asEmployeeResponseDto)
                                 .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeLightResponseDto> findByCompanyId(long companyId) {
        return employeeRepository.getEmployeesByCompanyId(companyId).stream()
                                 .map(EmployeeBasicInfoConverter::asEmployeeLightResponseDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto update(long employeeId, EmployeeUpdateInfo employeeUpdateInfo) {
        Employee currentEmployee = employeeRepository.findById(employeeId).orElseThrow(
            () -> new EmployeeNotFoundException(EMPLOYEE_NOT_FOUND + " " + employeeId));
        Employee updatedEmployee = EmployeeBasicInfoConverter.asEmployeeModel(employeeUpdateInfo);
        updatedEmployee.setId(currentEmployee.getId());
        updatedEmployee.setCompany(currentEmployee.getCompany());
        return EmployeeBasicInfoConverter.asEmployeeResponseDto(employeeRepository.save(updatedEmployee));
    }

    @Override
    public void removeEmployee(long employeeId) {
        try {
            employeeRepository.deleteById(employeeId);
        } catch (EmptyResultDataAccessException ex) {
            throw new EmployeeNotFoundException(String.format("Employee with Id : %s not found", employeeId));
        }

    }

    @Override
    public Double findAverageSalary(long companyId) {
        return Objects.requireNonNullElse(employeeRepository.averageSalaryByCompanyId(companyId), 0.0);
    }

}
