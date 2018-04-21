package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findUserRoleByName(String name);
}
