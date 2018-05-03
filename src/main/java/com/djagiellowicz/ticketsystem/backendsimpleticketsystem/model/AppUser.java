package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @NotNull
    @Column(unique=true)
    @Size(min=3)
    private String login;
    private String name;
    private String surname;
    @Size(min=3)
    @NotNull
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
    @OneToMany(mappedBy = "assignedTo", cascade = CascadeType.PERSIST)
    private List<Incident> assignedToIncidentsList;



}


