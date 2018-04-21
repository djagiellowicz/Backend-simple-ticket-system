package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Page<Incident> findAllBy(Pageable pageable);
}
