package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppUserDTO {
    private String login;
    private String name;
    private String surname;
    private String password;
}
