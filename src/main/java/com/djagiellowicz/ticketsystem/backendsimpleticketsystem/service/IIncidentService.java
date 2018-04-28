package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ObjectResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;

public interface IIncidentService {
    PageResponse<IncidentHeaderVO> getPageIncidents(int page);
    PageResponse<IncidentHeaderVO> getPageIncidents();
    void createIncident(IncidentDTO incidentDTO) throws UserDoesNotExistsOrIsNotLoggedInException;
    void changeStatus(Long incidentId, int statusId)
            throws  IncidentDoesNotExistsException, ThereIsNoSuchStatusException;
    void updateIncident(Incident incident, Long userId)
            throws UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException;
    void deleteIncident(Long incidentId, Long userId) throws IncidentDoesNotExistsException;
    public ObjectResponse<IncidentVO> getIncident(long id) throws IncidentDoesNotExistsException;
}
