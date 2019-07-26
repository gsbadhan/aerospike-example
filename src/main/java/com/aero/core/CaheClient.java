package com.aero.core;

import java.util.Properties;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.policy.AuthMode;
import com.aerospike.client.policy.BatchPolicy;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.InfoPolicy;
import com.aerospike.client.policy.Policy;
import com.aerospike.client.policy.QueryPolicy;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;

public class CaheClient {
    private CaheClient() {
    }

    protected static final int    DEFAULT_TIMEOUT_MS = 1000;
    public static AerospikeClient client             = null;

    public static void load(Properties params) {
        ClientPolicy policy = new ClientPolicy();
        policy.user = params.getProperty("user");
        policy.password = params.getProperty("password");
        policy.authMode = AuthMode.INTERNAL;
        policy.readPolicyDefault = new Policy();
        policy.writePolicyDefault = new WritePolicy();
        policy.scanPolicyDefault = new ScanPolicy();
        policy.queryPolicyDefault = new QueryPolicy();
        policy.batchPolicyDefault = new BatchPolicy();
        policy.infoPolicyDefault = new InfoPolicy();
        policy.timeout = DEFAULT_TIMEOUT_MS;
        Host[] hosts = Host.parseHosts(params.getProperty("host"), Integer.parseInt(params.getProperty("port")));
        client = new AerospikeClient(policy, hosts);
        client.getNodeNames().forEach(s -> System.out.println("node name:" + s));
    }

}
