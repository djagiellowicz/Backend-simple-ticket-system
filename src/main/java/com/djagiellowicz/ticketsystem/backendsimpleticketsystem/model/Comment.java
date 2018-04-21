package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDateTime creationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppUser postedBy;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Incident incident;


}
