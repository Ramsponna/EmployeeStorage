package com.test.testtask;

import com.test.entity.Company;

import com.test.service.impl.CompanyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceImplTest {

    @Autowired
    CompanyServiceImpl companyService;

    @Test
    public void createCompanyTest() {
        Company company=new Company();
        company.setName("TestCompany1");
        companyService.createCompany(company);
        assertEquals("TestCompany1",companyService.getCompany(1L).get().getName());
    }
}
