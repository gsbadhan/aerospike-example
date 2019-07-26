package com.aero.repo;

import java.util.Optional;

import com.aero.core.CaheClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.policy.WritePolicy;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private String     namsespace;
    private String     set;
    // ttl in seconds
    private static int EXPIRATION_SECONDS = (60 * 1000);

    public EmployeeRepositoryImpl(String namsespace, String set) {
        this.namsespace = namsespace;
        this.set = set;
    }

    @Override
    public boolean save(Employee emp) {
        WritePolicy wPolicy = new WritePolicy(CaheClient.client.getWritePolicyDefault());
        wPolicy.sendKey = true;
        wPolicy.expiration = EXPIRATION_SECONDS;
        CaheClient.client.put(wPolicy, new Key(namsespace, set, emp.getId()), new Bin("data", emp));
        System.out.println("saved..");
        return true;
    }

    @Override
    public Optional<Employee> getEmployee(long id) {
        Key key = new Key(namsespace, set, id);
        Record record = CaheClient.client.get(CaheClient.client.readPolicyDefault, key);
        if (record == null)
            return Optional.empty();

        Object emp = record.getValue("data");
        if (emp == null)
            return Optional.empty();

        return Optional.of((Employee) emp);
    }

}
