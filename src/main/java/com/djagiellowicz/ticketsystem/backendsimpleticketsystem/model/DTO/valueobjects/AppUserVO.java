package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class AppUserVO extends AppUserHeaderVO {
    private Set<UserRole> userRoleSet;
    private List<IncidentHeaderVO> createdIncidentsList;
    private List<IncidentHeaderVO> assignedToIncidentsList;
}
