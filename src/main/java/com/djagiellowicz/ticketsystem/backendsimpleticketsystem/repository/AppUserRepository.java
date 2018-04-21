package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String login);
    Page<AppUser> findAllBy(Pageable pageable);
}
