package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ObjectResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public void updateIncident(Incident incident) throws IncidentDoesNotExistsException {
        Optional<Incident> incidentById = incidentRepository.findById(incident.getId());
            if (incidentById.isPresent()){
                incidentRepository.save(incident);
            }
            else{
                throw new IncidentDoesNotExistsException();
            }
    }

    @Override
    public void deleteIncident(Long incidentId) throws IncidentDoesNotExistsException {
        Optional<Incident> byId = incidentRepository.findById(incidentId);
        if (byId.isPresent()){
            Incident incident = byId.get();
            incidentRepository.delete(incident);
        }
        else{
            throw new IncidentDoesNotExistsException();
        }

    }

    @Override
    public PageResponse<IncidentHeaderVO> getPageIncidents(int page){
        Page<Incident> allBy = incidentRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        long totalElements = allBy.getTotalElements();
        int totalPages = allBy.getPageable().getPageNumber();

        List<IncidentHeaderVO> content = allBy.getContent().stream().map(
                element -> new IncidentHeaderVO(element.getId(),element.getTitle(),element.getDescription(),
                element.getCreationDate(),element.getStatus()))
                .collect(Collectors.toList());

        return new PageResponse<>(totalElements,totalPages,content);
    }

    @Override
    public PageResponse<IncidentHeaderVO> getPageIncidents(){
        return getPageIncidents(0);
    }

    @Override
    public ObjectResponse<IncidentVO> getIncident(long id) throws IncidentDoesNotExistsException{
        Optional<Incident> byId = incidentRepository.findById(id);
        if (byId.isPresent()){
            Incident incident = byId.get();
            AppUser assignedTo = incident.getAssignedTo();
            AppUser createdBy = incident.getCreatedBy();

            AppUserHeaderVO appUserHeaderVOAssignedTo = new AppUserHeaderVO();
            AppUserHeaderVO appUserHeaderVOCreatedBy = new AppUserHeaderVO();

            if (assignedTo != null) {
                appUserHeaderVOAssignedTo = new AppUserHeaderVO(assignedTo.getId(), assignedTo.getLogin(),
                        assignedTo.getName(), assignedTo.getSurname());
            }
            if (createdBy != null) {
                appUserHeaderVOCreatedBy = new AppUserHeaderVO(createdBy.getId(), createdBy.getLogin(),
                        createdBy.getName(), createdBy.getSurname());
            }

            IncidentVO incidentVO = new IncidentVO(incident.getId(),incident.getTitle(),incident.getDescription(),
                    incident.getCreationDate(),incident.getStatus(), appUserHeaderVOCreatedBy, appUserHeaderVOAssignedTo);

            return new ObjectResponse<>(incidentVO);
        }
        throw new IncidentDoesNotExistsException();
    }

    @Override
    public void changeStatus(Long incidentId, int statusId)
            throws  IncidentDoesNotExistsException, ThereIsNoSuchStatusException {
        Optional<Incident> incidentById = incidentRepository.findById(incidentId);

        if(incidentById.isPresent()){
            Incident incident = incidentById.get();
            IncidentStatus[] values = IncidentStatus.values();

            for (IncidentStatus availableStatus: values) {
                if (availableStatus.getValue() == statusId){
                    System.out.println(availableStatus.getValue() + " id przeslane: " + statusId);
                    incident.setStatus(availableStatus);
                    incidentRepository.save(incident);
                    return;
                }
            }
            throw new ThereIsNoSuchStatusException();
        }
        throw new IncidentDoesNotExistsException();
    }

    @Override
    public ListResponse<IncidentStatus> getStatuses(){
        IncidentStatus[] values = IncidentStatus.values();
        List<IncidentStatus> incidentStatuses = Arrays.asList(values);
        return new ListResponse<>(incidentStatuses);
    }
}
