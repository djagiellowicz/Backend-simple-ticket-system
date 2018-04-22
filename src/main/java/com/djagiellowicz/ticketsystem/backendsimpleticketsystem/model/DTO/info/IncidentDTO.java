package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentDTO {
    private String title;
    private String description;
    private long createdById;
}
