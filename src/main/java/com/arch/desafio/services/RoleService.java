package com.arch.desafio.services;

import com.arch.desafio.models.Role;
import com.arch.desafio.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    public Role findRoleByNameContainingIgnoreCase(String roleName) {
        return this.roleRepository.findRoleByNameContainingIgnoreCase(roleName);
    }
}
