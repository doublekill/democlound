package com.jincin.security.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long Id;
    private String RoleStatus;
    private String Options;

    public String getOptions() {
        return Options;
    }

    public void setOptions(String options) {
        Options = options;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRoleStatus() {
        return RoleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        RoleStatus = roleStatus;
    }
}
