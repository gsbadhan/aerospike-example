package com.aero.repo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Employee implements Serializable {
    private long   id;
    private String name;
    private String dept;

    public Employee(long id, String name, String dept) {
        super();
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dept=" + dept + "]";
    }
    
    

}
