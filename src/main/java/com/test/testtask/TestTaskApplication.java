package com.test.testtask;

import com.test.entity.Company;
import com.test.entity.Employee;
import com.test.repository.CompanyRepository;
import com.test.repository.EmployeeRepository;
import com.test.service.CompanyService;
import com.test.service.EmployeeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.test")
@EntityScan("com.test.entity")
@EnableJpaRepositories("com.test.repository")
public class TestTaskApplication {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CompanyRepository companyRepository;

    public static void main(String[] args) {
        SpringApplication.run(TestTaskApplication.class, args);
    }

    @Bean
    InitializingBean loadSampleData(){
        return () -> {
            Company company1=new Company();
            company1.setName("TestCompany1");
            companyRepository.save(company1);
            Company company2=new Company();
            company2.setName("TestCompany2");
            companyRepository.save(company2);
            Employee employee1=new Employee();
            employee1.setName("TestEmp1");
            employee1.setSurname("LastName1");
            employee1.setEmail("TestEmp1@test.com");
            employee1.setSalary(2000.0);
            employee1.setAddress("testAddr1");
            employee1.setCompany(company1);
            employeeRepository.save(employee1);
            //2nd Employee
            Employee employee2=new Employee();
            employee2.setName("TestEmp2");
            employee2.setSurname("LastName2");
            employee2.setEmail("TestEmp2@test.com");
            employee2.setSalary(3000.0);
            employee2.setAddress("testAddr2");
            employee2.setCompany(company1);
            employeeRepository.save(employee2);

            //3nd Employee
            Employee employee3=new Employee();
            employee3.setName("TestEmp3");
            employee3.setSurname("LastName31");
            employee3.setEmail("TestEmp3@test.com");
            employee3.setSalary(1000.0);
            employee3.setAddress("testAddr3");
            employee3.setCompany(company1);
            employeeRepository.save(employee3);
        };
    }

}
