package com.jincin.security.dao;

import com.jincin.security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleDao extends JpaRepository<Role,Long> {
    @Query(value = "SELECT * FROM role WHERE id = (SELECT a.roles_id FROM user_roles a WHERE a.user_id = ?1)", nativeQuery = true)
    Role findById(Long id);
//    @Query("SELECT c.options FROM c.role WHERE c.id = (SELECT a.roles_id FROM user_roles a WHERE a.user_id = :id)")
//    Role findById(@Param("id") Long id);
}
