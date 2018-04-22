package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Incident incident;
    @OneToMany(mappedBy = "changeLog")
    private List<IncidentChange> incidentChangeList;

    public ChangeLog(){
        this.incidentChangeList = new ArrayList<>();
    }
}
