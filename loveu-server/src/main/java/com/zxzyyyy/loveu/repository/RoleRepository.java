package com.zxzyyyy.loveu.repository;

import com.zxzyyyy.loveu.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
