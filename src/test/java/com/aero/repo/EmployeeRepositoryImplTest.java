package com.aero.repo;

import java.util.Optional;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.aero.core.CaheClient;

public class EmployeeRepositoryImplTest {
    private EmployeeRepository repository;

    @Before
    public void init() {
        Properties params = new Properties();
        params.put("user", "");
        params.put("password", "");
        params.put("host", "172.28.128.3");
        params.put("port", "3000");
        CaheClient.load(params);
        repository = new EmployeeRepositoryImpl("test", "emp");
    }

    @Test
    public void saveAndGetTest() {
        for (int i = 1001; i < 1050; i++) {
            Employee emp = new Employee(i, "gs", "ss");
            repository.save(emp);
            Optional<Employee> cacheEmp = repository.getEmployee(i);
            System.out.println(cacheEmp.get());
        }
    }

}
