package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentUpdateDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.*;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
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

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response> createIncident(@RequestBody IncidentDTO incidentDTO) {
        try {
            incidentService.createIncident(incidentDTO);
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.created();
    }

    @RequestMapping(path = "/update")
    public ResponseEntity<Response> updateIncident(@RequestBody IncidentUpdateDTO incidentUpdateDTO) {
        try {
            incidentService.updateIncident(incidentUpdateDTO);
        } catch (IncidentDoesNotExistsException e) {
            return ResponseFactory.badRequest();
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        }

        return ResponseFactory.created();
    }

    @RequestMapping(path = "/delete/{incidentid}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> deleteIncident(@PathVariable("incidentid") Long incidentId) {
        try {
            incidentService.deleteIncident(incidentId);
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
    public ResponseEntity<PageResponse<IncidentHeaderVO>> getIncidents() {
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
    @RequestMapping(path = "/status/{incidentId}")
    public ResponseEntity<Response> changeStatus(@PathVariable("incidentId") long incidentId, @RequestParam("statusid") int statusId){
        try {
            incidentService.changeStatus(incidentId,statusId);
        } catch (IncidentDoesNotExistsException e) {
            return ResponseFactory.badRequest();
        } catch (ThereIsNoSuchStatusException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.ok("Status has been changed");
    }

    @RequestMapping(path = "/status/list")
    public ResponseEntity<ListResponse<IncidentStatus>> getStatuses(){
        ListResponse<IncidentStatus> statuses = incidentService.getStatuses();
        return ResponseFactory.listResponse(statuses);
    }
}
