package com.zxzyyyy.loveu.service;

import com.zxzyyyy.loveu.entity.Auth;
import com.zxzyyyy.loveu.entity.Role;
import com.zxzyyyy.loveu.repository.AuthRepository;
import com.zxzyyyy.loveu.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 */
@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findById(int id) {
        return roleRepository.findById(id);
    }
}
