package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncidentDTO {
    private String title;
    private String description;
    private long assignedToId;
    private long createdById;
}
