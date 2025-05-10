package com.example.account.repositories;

import com.example.account.entities.RoleName;
import com.example.account.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Roles, String> {
    Optional<Roles> findByName(RoleName name);

}
