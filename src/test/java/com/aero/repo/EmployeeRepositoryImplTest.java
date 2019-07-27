package com.aero.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

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

    @Test
    public void deleteTest() {
        long id = 1001;
        boolean status = repository.delete(id);
        System.out.println("deleteTest:" + status);
    }

    @Test
    public void getEmployeeTest() {
        List<Long> ids = new ArrayList<>();
        ids.add(1001L);
        ids.add(1002L);
        ids.add(1003L);
        ids.add(1023L);
        Optional<List<Employee>> employees = repository.getEmployee(ids);
        employees.get().forEach(em -> System.out.println(em));
    }
    
    @Test
    public void existTest() {
        long id = 1001;
        boolean status = repository.exist(id);
        System.out.println("existTest:" + status);
    }

}
