package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public UserRole(String name) {
        this.name = name;
    }

    @ManyToOne
    AppUser appUser;
}
