package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private IncidentStatus status;

    @ManyToOne
    private AppUser assignedTo;
    @ManyToOne
    private AppUser createdBy;


    public Incident(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.status = IncidentStatus.NEW;
    }
}
