package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ObjectResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.Response;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.IIncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response> createIncident(@RequestBody IncidentDTO incidentDTO) {
        try {
            incidentService.createIncident(incidentDTO);
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
    @RequestMapping(path = "/list/{page}")
    public ResponseEntity<PageResponse<IncidentHeaderVO>> getIncidents(@PathVariable("page") int page) {
        PageResponse<IncidentHeaderVO> pageIncidents = incidentService.getPageIncidents(page);
        return ResponseFactory.pageResponse(pageIncidents);
    }
    @RequestMapping(path = "/list")
    public ResponseEntity<PageResponse<IncidentHeaderVO>> getIncidents(Long userId) {
        PageResponse<IncidentHeaderVO> pageIncidents = incidentService.getPageIncidents();
        return ResponseFactory.pageResponse(pageIncidents);
    }
    @RequestMapping(path = "/get/{id}")
    public ResponseEntity<ObjectResponse<IncidentVO>> getIncident(@PathVariable("id") Long userId) {
        try {
            ObjectResponse<IncidentVO> incident = incidentService.getIncident(userId);
            return ResponseFactory.objectRespone(incident);
        } catch (IncidentDoesNotExistsException e) {
            return ResponseFactory.objectResponeBad();
        }
    }
}
