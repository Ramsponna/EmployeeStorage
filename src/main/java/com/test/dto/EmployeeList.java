package com.test.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeList {
    private List<EmployeeLightResponseDto> employees;
}
