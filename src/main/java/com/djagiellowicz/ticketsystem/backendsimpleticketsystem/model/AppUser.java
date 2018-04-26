package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public AppUser(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRoleSet = new HashSet<>();
        this.userRoleSet.add(userRole);
    }

    public AppUser(String login, String name, String surname, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    @OneToMany(mappedBy = "appUser",fetch = FetchType.EAGER)
    private Set<UserRole> userRoleSet;
    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Incident> createdIncidentsList;
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.ALL)
    private List<Incident> assignedToIncidentsList;



}


