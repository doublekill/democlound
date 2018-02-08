package com.jincin.security.service;

import com.jincin.security.dao.RoleDao;
import com.jincin.security.dao.UserDao;
import com.jincin.security.domain.Role;
import com.jincin.security.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserService implements UserDetailsService{//自定义UserDetailsService
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /**
         * 这里面写登录校验规则，不管是手机号还是用户名登录，逻辑都在这里加。
         * 正确返回user，不正确抛UsernameNotFoundException异常，此方法类型为UserDetails所以user需要实现UserDetails
         */
        User user = userDao.findByUsername(username);
//        System.out.println("username:"+username);
//        System.err.println("username:"+user.getUsername()+";password:"+user.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public List<User> getUser(){
        List<User> list = userDao.findAll();
        return list;
    }

    public boolean authorized(String username,String permission,String permission1) {
        Long id = userDao.findByUsername(username).getId();
        Role role = roleDao.findById(id);
        String status = role.getRoleStatus();
        String options = role.getOptions();
        String[] arr = options.split(",");
            for (String a : arr) {
                if (permission1.equals(a)) {
                    if (permission.equals(status)) {
                        return true;
                    }
                }
            }
            return false;
    }
}
