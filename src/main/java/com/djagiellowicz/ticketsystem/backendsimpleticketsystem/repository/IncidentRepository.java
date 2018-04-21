package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
