package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentUpdateDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ObjectResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;

public interface IIncidentService {
    PageResponse<IncidentHeaderVO> getPageIncidents(int page);
    PageResponse<IncidentHeaderVO> getPageIncidents();
    void createIncident(IncidentDTO incidentDTO) throws UserDoesNotExistsOrIsNotLoggedInException;
    void changeStatus(Long incidentId, int statusId)
            throws  IncidentDoesNotExistsException, ThereIsNoSuchStatusException;
    void updateIncident(IncidentUpdateDTO incidentUpdateDTO)
            throws IncidentDoesNotExistsException, UserDoesNotExistsOrIsNotLoggedInException;
    void deleteIncident(Long incidentId) throws IncidentDoesNotExistsException;
    ObjectResponse<IncidentVO> getIncident(long id) throws IncidentDoesNotExistsException;
    ListResponse<IncidentStatus> getStatuses();
}
