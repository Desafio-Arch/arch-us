package com.arch.desafio.repositories;

import com.arch.desafio.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByNameContainingIgnoreCase(String name);
}
