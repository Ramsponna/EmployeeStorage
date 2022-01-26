package com.test.repository;

import com.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e from Employee e inner join e.company c where c.id=?1")
    List<Employee> getEmployeesByCompanyId(Long id);

    @Query(value = "SELECT avg(e.salary) FROM Employee e inner join e.company c where c.id=?1")
    Double averageSalaryByCompanyId(long companyId);
}
