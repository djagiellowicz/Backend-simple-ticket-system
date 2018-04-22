package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.IncidentDoesNotExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.ThereIsNoSuchStatusException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Comment;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.IncidentDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.Incident;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.IncidentStatus;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.CommentRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.IncidentChangeRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.IncidentRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Service
public class IncidentService implements IIncidentService {

    private IncidentRepository incidentRepository;
    private IncidentChangeRepository incidentChangeRepository;
    private CommentRepository commentRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository, IncidentChangeRepository incidentChangeRepository,
                           CommentRepository commentRepository, AppUserRepository appUserRepository) {
        this.incidentRepository = incidentRepository;
        this.incidentChangeRepository = incidentChangeRepository;
        this.commentRepository = commentRepository;
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
    public void createIncident(IncidentDTO incidentDTO, Long userId) throws UserDoesNotExistsOrIsNotLoggedInException {
        Optional<AppUser> byId = appUserRepository.findById(userId);
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
    public PageResponse<Incident> getPageIncidents(int page){
        Page<Incident> allBy = incidentRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        return new PageResponse<>(allBy);
    }

    @Override
    public PageResponse<Incident> getPageIncidents(){
        return getPageIncidents(0);
    }

    @Override
    public void addComment(Comment comment, Long userId, Long incidentId)
            throws  UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException
    {
        Optional<AppUser> appUserById = appUserRepository.findById(userId);
        Optional<Incident> incidentById = incidentRepository.findById(incidentId);
        if (appUserById.isPresent()){
            AppUser appUser = appUserById.get();
            if (incidentById.isPresent()){
                Incident incident = incidentById.get();

                comment.setPostedBy(appUser);
                comment.setCreationDate(LocalDateTime.now());
                comment.setIncident(incident);
                incident.getCommentList().add(comment);
                commentRepository.save(comment);
            }
            else{
                throw new IncidentDoesNotExistsException();
            }
        }
        else {
            throw new UserDoesNotExistsOrIsNotLoggedInException();
        }
    }

    @Override
    public void changeStatus(Long userId, Long incidentId, int statusId)
            throws  UserDoesNotExistsOrIsNotLoggedInException, IncidentDoesNotExistsException,
                    ThereIsNoSuchStatusException{
        Optional<AppUser> appUserById = appUserRepository.findById(userId);
        Optional<Incident> incidentById = incidentRepository.findById(incidentId);

        if (appUserById.isPresent()){
//  TODO: It will be needed if ChangeLog works  AppUser appUser = appUserById.get();
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
