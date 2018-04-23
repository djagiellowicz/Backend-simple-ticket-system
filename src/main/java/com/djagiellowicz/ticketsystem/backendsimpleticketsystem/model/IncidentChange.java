package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class IncidentChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime changeDate;

    @ManyToOne
    private ChangeLog changeLog;
    @ManyToOne
    private AppUser appUser;
}
