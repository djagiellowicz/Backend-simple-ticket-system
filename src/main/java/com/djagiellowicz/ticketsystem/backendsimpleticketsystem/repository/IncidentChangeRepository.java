package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentChange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentChangeRepository extends JpaRepository<IncidentChange,Long> {
}
