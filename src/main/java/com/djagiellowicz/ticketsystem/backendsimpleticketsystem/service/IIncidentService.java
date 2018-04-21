package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;

public interface IIncidentService {
    PageResponse<Incident> getPageIncidents(int page);
    PageResponse<Incident> getPageIncidents();
    void createIncident(Incident incident, Long userId) throws UserDoesNotExistsOrIsNotLoggedInException;

}
