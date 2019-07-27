package com.aero.repo;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    boolean save(Employee emp);

    Optional<Employee> getEmployee(long id);

    Optional<List<Employee>> getEmployee(List<Long> ids);

    boolean delete(long id);
}
