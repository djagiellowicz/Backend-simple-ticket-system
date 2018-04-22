package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "incident")
    private List<Comment> commentList;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppUser assignedTo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppUser createdBy;
    @OneToOne
    private ChangeLog changeLog;

    public Incident(String title, String description) {
        this.title = title;
        this.description = description;
        this.creationDate = LocalDateTime.now();
        this.status = IncidentStatus.NEW;
        this.commentList = new ArrayList<>();
        this.changeLog = new ChangeLog();
    }
}
