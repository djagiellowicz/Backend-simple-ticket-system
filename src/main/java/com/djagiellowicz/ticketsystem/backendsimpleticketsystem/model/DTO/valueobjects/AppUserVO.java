package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class AppUserVO extends AppUserHeaderVO {
    private Set<UserRole> userRoleSet;
    private List<IncidentHeaderVO> createdIncidentsList;
    private List<IncidentHeaderVO> assignedToIncidentsList;

    public AppUserVO(Long id, String login, String name, String surname, Set<UserRole> userRoleSet, List<IncidentHeaderVO> createdIncidentsList, List<IncidentHeaderVO> assignedToIncidentsList) {
        super(id, login, name, surname);
        this.userRoleSet = userRoleSet;
        this.createdIncidentsList = createdIncidentsList;
        this.assignedToIncidentsList = assignedToIncidentsList;
    }
}
