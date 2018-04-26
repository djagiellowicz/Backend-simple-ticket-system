package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects;


import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidentHeaderVO {
    protected Long id;
    protected String title;
    protected String description;
    protected LocalDateTime creationDate;
    protected IncidentStatus status;
}
