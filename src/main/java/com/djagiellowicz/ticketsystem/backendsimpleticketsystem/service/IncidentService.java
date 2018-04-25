package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Service
public class IncidentService implements IIncidentService {

    private IncidentRepository incidentRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository,  AppUserRepository appUserRepository) {
        this.incidentRepository = incidentRepository;

        this.appUserRepository = appUserRepository;
    }

//    @Override
//    public void createIncident(Incident incident, Long userId) throws UserDoesNotExistsOrIsNotLoggedInException {
//        Optional<AppUser> byId = appUserRepository.findById(userId);
//        if (byId.isPresent())
//        {
//            AppUser appUser = byId.get();
//            incident.setCreatedBy(appUser);
//            incident.setStatus(IncidentStatus.NEW);
//            incidentRepository.save(incident);
//        }
//        else{
//            throw new UserDoesNotExistsOrIsNotLoggedInException();
//        }
//
//    }

    @Override
    public void createIncident(IncidentDTO incidentDTO) throws UserDoesNotExistsOrIsNotLoggedInException {
        Optional<AppUser> byId = appUserRepository.findById(incidentDTO.getCreatedById());
        if (byId.isPresent())
        {
            Incident incident = new Incident(incidentDTO.getTitle(),incidentDTO.getDescription());

            AppUser appUser = byId.get();
            incident.setCreatedBy(appUser);
            incident.setStatus(IncidentStatus.NEW);

            incidentRepository.save(incident);
        }
        else{
            throw new UserDoesNotExistsOrIsNotLoggedInException();
        }

    }

    @Override
    public void updateIncident(Incident incident, Long userId)
            throws UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException {
        Optional<AppUser> appUserById = appUserRepository.findById(userId);
        Optional<Incident> incidentById = incidentRepository.findById(incident.getId());

        if (appUserById.isPresent()){
            if (incidentById.isPresent()){
                incidentRepository.save(incident);
            }
            else{
                throw new IncidentDoesNotExistsException();
            }
        }
        else{
            throw new UserDoesNotExistsOrIsNotLoggedInException();
        }

    }

    @Override
    public void deleteIncident(Long incidentId, Long userId) throws IncidentDoesNotExistsException {
        Optional<Incident> byId = incidentRepository.findById(incidentId);
        if (byId.isPresent()){
            Incident incident = byId.get();
            incidentRepository.delete(incident);
        }
        else{
            throw new IncidentDoesNotExistsException();
        }

    }

    //TODO: To be changed to IncidentDTO

    @Override
    public PageResponse<Incident> getPageIncidents(int page){
        Page<Incident> allBy = incidentRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        long totalElements = allBy.getTotalElements();
        int totalPages = allBy.getTotalPages();
        List<Incident> content = allBy.getContent();
        return new PageResponse<>(totalElements,totalPages,content);
    }

    //TODO: To be changed to IncidentDTO

    @Override
    public PageResponse<Incident> getPageIncidents(){
        return getPageIncidents(0);
    }

    @Override
    public void changeStatus(Long userId, Long incidentId, int statusId)
            throws  UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException,
                    ThereIsNoSuchStatusException{
        Optional<AppUser> appUserById = appUserRepository.findById(userId);
        Optional<Incident> incidentById = incidentRepository.findById(incidentId);

        if (appUserById.isPresent()){
            if (incidentById.isPresent()){
                Incident incident = incidentById.get();
                IncidentStatus[] incidentStatus = IncidentStatus.values();
                for (IncidentStatus status : incidentStatus) {
                    if (status.getValue() == statusId){
                        incident.setStatus(status);
                        incidentRepository.save(incident);
                        return;
                    }
                }
                throw new ThereIsNoSuchStatusException();
            }
            else{
                throw new IncidentDoesNotExistsException();
            }
        }
        else {
            throw new UserDoesNotExistsOrIsNotLoggedInException();
        }
    }
}
