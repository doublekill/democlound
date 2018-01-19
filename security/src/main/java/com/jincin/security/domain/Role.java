package com.jincin.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private long Id;
    private String RoleStatus;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRoleStatus() {
        return RoleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        RoleStatus = roleStatus;
    }
}
