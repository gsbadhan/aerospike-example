package com.aero.repo;

import java.util.Optional;

public interface EmployeeRepository {
    boolean save(Employee emp);

    Optional<Employee> getEmployee(long id);
}
