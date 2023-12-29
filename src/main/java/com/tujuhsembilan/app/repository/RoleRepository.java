package com.tujuhsembilan.app.repository;

import org.springframework.stereotype.Repository;

import com.tujuhsembilan.app.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID>{
    
    public Role findByRoleId(UUID roleId);
    public Role findByRoleName(String roleName);
}
