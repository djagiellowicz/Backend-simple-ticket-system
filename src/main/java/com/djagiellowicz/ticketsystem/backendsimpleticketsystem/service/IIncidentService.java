package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Comment;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;

public interface IIncidentService {
    PageResponse<Incident> getPageIncidents(int page);
    PageResponse<Incident> getPageIncidents();
    void createIncident(Incident incident, Long userId) throws UserDoesNotExistsOrIsNotLoggedInException;
    void addComment(Comment comment, Long userId, Long incidentId)
            throws  UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException;
    void changeStatus(Long userId, Long incidentId, int statusId)
            throws  UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException,
            ThereIsNoSuchStatusException;

}
