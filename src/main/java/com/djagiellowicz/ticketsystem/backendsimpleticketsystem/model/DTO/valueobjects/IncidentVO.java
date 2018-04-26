package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncidentVO extends IncidentHeaderVO {
    private AppUserHeaderVO assignedTo;
    private AppUserHeaderVO createdBy;

}
