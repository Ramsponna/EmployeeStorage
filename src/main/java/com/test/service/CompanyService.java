package com.test.service;

import com.test.entity.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompanies();

    void createCompany(Company company);

    Optional<Company> getCompany(Long id);

}
