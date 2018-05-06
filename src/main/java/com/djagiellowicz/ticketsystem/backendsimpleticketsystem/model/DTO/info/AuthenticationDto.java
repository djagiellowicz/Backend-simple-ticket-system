package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {
    private String token;
    private AppUser user;
}
