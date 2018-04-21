package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String name;
    private String surname;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<UserRole> userRoleSet;
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.PERSIST)
    private List<Incident> createdIncidentsList;
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.PERSIST)
    private List<Incident> assignedToIncidentsList;
    @OneToMany(mappedBy = "postedBy", cascade = CascadeType.PERSIST)
    private List<Comment> commentList;
    @OneToMany(mappedBy = "appUser", cascade = CascadeType.PERSIST)
    private List<IncidentChange> incidentChangeList;
}


