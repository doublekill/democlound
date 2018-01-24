package com.jincin.service;

import com.jincin.Exception.LogicException;
import com.jincin.dao.UserDao;
import com.jincin.domain.ProviderUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr_Li on 2017/7/11.
 */
@Service
public class UserServiceImpl {

    @Autowired private UserDao userDao;
    public String register(ProviderUser providerUser){
        userDao.save(providerUser);
        return "OK";
    }

//    public ProviderUser login(String username) {
//        ProviderUser providerUser = userDao.findProviderUserByUsername(username);
//        return providerUser;
//    }

    public String validate(ProviderUser providerUser){
        if(providerUser == null){
            return null;
        }
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("userId","is_deleted")
//                .withIgnoreCase() //忽略大小写
//              //.withMatcher("author",match->match.contains())处理模糊查询 "%+key+%"
                .withMatcher("phone",ExampleMatcher.GenericPropertyMatchers.startsWith())
                ;
        Example<ProviderUser> example = Example.of(providerUser,matcher);
        boolean exits = userDao.exists(example);
        if (!exits){
            throw new LogicException("10001","用户不存在或者密码错误！请确认后再输..");
        }
        return "OK";
    }

    public ProviderUser findOne(int userId) {
        ProviderUser pu;
        pu = userDao.findOne(userId);
        if (pu == null){
            throw new LogicException("10001","该用户不存在");
        }
        return pu;
    }

    public List<ProviderUser> findAll() {
        List<ProviderUser> list = userDao.findAll();
        return list;
    }

}
