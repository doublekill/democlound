package com.jincin.security.controller;

import com.jincin.security.domain.User;
import com.jincin.security.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private CustomUserService customUserService;

    @GetMapping(value = "/getUser")
    @PreAuthorize("authenticated and hasPermission('ROLE_USER', 'ROLE_SEL')")
    public List<User> getUser(){
        return customUserService.getUser();
    }
}
