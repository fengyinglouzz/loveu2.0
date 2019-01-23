package com.zxzyyyy.loveu.repository;

import com.zxzyyyy.loveu.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findById(int id);
}
