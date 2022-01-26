package com.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private Double salary;
    private Long companyId;
    private String companyName;
}
