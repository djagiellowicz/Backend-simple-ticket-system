package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.Response;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.IIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/incident")
public class IncidentController {

    private IIncidentService incidentService;

    @Autowired
    public IncidentController(IIncidentService incidentService) {
        this.incidentService = incidentService;
    }

    @RequestMapping(path = "/create")
    public ResponseEntity<Response> createIncident(@RequestBody IncidentDTO incidentDTO, Long userId) {
        try {
            incidentService.createIncident(incidentDTO, userId);
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.created();
    }

    @RequestMapping(path = "/update")
    public ResponseEntity<Response> updateIncident(@RequestBody Incident incident, Long userId) {
        try {
            incidentService.updateIncident(incident, userId);
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        } catch (IncidentDoesNotExistsException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.created();
    }

    @RequestMapping(path = "/delete")
    public ResponseEntity<Response> deleteIncident(Long incidentId, Long userId) {
        try {
            incidentService.deleteIncident(incidentId, userId);
        } catch (IncidentDoesNotExistsException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.deleted();

    }
    @RequestMapping(path = "/list")
    public ResponseEntity<PageResponse<Incident>> getIncident(Long incidentId, Long userId) {
        PageResponse<Incident> pageIncidents = incidentService.getPageIncidents();
        return ResponseFactory.pageResponse(pageIncidents);
    }
}
