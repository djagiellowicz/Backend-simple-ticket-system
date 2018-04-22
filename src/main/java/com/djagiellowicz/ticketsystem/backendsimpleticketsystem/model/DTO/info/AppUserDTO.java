package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO {
    private String login;
    private String name;
    private String surname;
    private String password;
}
