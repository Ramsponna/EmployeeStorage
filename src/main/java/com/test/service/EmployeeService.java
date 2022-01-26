package com.test.service;

import com.test.dto.EmployeeInsertInfo;
import com.test.dto.EmployeeLightResponseDto;
import com.test.dto.EmployeeResponseDto;
import com.test.dto.EmployeeUpdateInfo;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(EmployeeInsertInfo employeeInsertInfo);

    List<EmployeeResponseDto> findAll();

    EmployeeResponseDto update(long employeeId, EmployeeUpdateInfo employeeUpdateInfo);

    void removeEmployee(long employeeId);

    Double findAverageSalary(long companyId);

    EmployeeResponseDto findByEmployeeId(long employeeId);

    List<EmployeeLightResponseDto> findByCompanyId(long companyId);


}
