package com.jincin.dao;

import com.jincin.domain.ProviderUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Mr_Li on 2017/7/11.
 */
@Repository
public interface UserDao extends JpaRepository<ProviderUser,Integer> {
//    @Query("select username,password from ProviderUser where username=:username and password=:password")
//    boolean login(@Param("username") String name,@Param("password") String password);
//    ProviderUser findProviderUserByUsername(String username);
}
