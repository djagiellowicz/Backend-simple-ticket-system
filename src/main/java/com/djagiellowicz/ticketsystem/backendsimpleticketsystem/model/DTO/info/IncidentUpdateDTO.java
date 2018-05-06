package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentUpdateDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime creationDate;
    private IncidentStatus status;
    private Long createdById;
    private Long assignedToId;
}
