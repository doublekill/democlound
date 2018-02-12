package com.jincin.security.config;

import com.jincin.security.dao.UserDao;
import com.jincin.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private CustomUserService customUserService;

    @Override
    public boolean hasPermission(Authentication authentication, Object o, Object o1) {
        String username = authentication.getName();
        return customUserService.authorized(username, o.toString(), o1.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return true;
    }
}