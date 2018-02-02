package com.jincin.security.dao;

import com.jincin.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr_Li on 2017/7/11.
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
    User findByUsername(String username);
}
