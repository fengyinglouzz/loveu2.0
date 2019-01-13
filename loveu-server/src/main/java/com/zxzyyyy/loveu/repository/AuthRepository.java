package com.zxzyyyy.loveu.repository;

import com.zxzyyyy.loveu.entity.Auth;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface AuthRepository extends JpaRepository<Auth, Integer>{
    Auth findByUsername(String username);
}
