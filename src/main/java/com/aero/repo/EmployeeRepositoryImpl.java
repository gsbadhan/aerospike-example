package com.aero.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aero.core.CaheClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.RecordExistsAction;
import com.aerospike.client.policy.WritePolicy;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private String     namsespace;
    private String     set;
    // ttl in seconds
    private static int EXPIRATION_SECONDS = 60;

    public EmployeeRepositoryImpl(String namsespace, String set) {
        this.namsespace = namsespace;
        this.set = set;
    }

    @Override
    public boolean save(Employee emp) {
        WritePolicy wPolicy = new WritePolicy(CaheClient.client.getWritePolicyDefault());
        wPolicy.sendKey = true;
        wPolicy.expiration = EXPIRATION_SECONDS;
        wPolicy.recordExistsAction = RecordExistsAction.REPLACE;
        CaheClient.client.put(wPolicy, new Key(namsespace, set, emp.getId()), new Bin("data", emp));
        System.out.println("saved..");
        return true;
    }

    @Override
    public Optional<Employee> getEmployee(long id) {
        Key key = new Key(namsespace, set, id);
        Record record = CaheClient.client.get(CaheClient.client.getReadPolicyDefault(), key);
        if (record == null)
            return Optional.empty();

        Object emp = record.getValue("data");
        if (emp == null)
            return Optional.empty();

        return Optional.of((Employee) emp);
    }

    @Override
    public Optional<List<Employee>> getEmployee(List<Long> ids) {
        Key[] keys = new Key[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            keys[i] = new Key(namsespace, set, ids.get(i));
        }
        Record[] records = CaheClient.client.get(CaheClient.client.getBatchPolicyDefault(), keys);

        if (records == null || records.length == 0)
            return Optional.empty();

        List<Employee> employees = new ArrayList<>(records.length);
        for (Record record : records) {
            employees.add((Employee) record.getValue("data"));
        }
        return Optional.of(employees);
    }

    @Override
    public boolean delete(long id) {
        Key key = new Key(namsespace, set, id);
        return CaheClient.client.delete(CaheClient.client.getWritePolicyDefault(), key);
    }

}
