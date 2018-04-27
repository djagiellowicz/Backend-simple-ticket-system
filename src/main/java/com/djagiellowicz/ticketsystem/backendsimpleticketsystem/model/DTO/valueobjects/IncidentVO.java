package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidentVO extends IncidentHeaderVO {
    private AppUserHeaderVO assignedTo;
    private AppUserHeaderVO createdBy;

    public IncidentVO(Long id, String title, String description, LocalDateTime creationDate, IncidentStatus status,
                      AppUserHeaderVO createdBy, AppUserHeaderVO assignedTo) {
        super(id, title, description, creationDate, status);
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
    }
}
