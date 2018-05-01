package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.IncidentHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.beans.support.PagedListHolder.DEFAULT_PAGE_SIZE;

@Service
public class AppUserService implements IAppUserService {

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void register(AppUserDTO appUserDTO) throws UserAlreadyExistsException {
        AppUser appUser = new AppUser(appUserDTO.getLogin(),appUserDTO.getName(),appUserDTO.getSurname()
                ,appUserDTO.getPassword());

        appUser.setLogin(appUser.getLogin().toLowerCase());

        Optional<AppUser> byLogin = appUserRepository.findByLogin(appUser.getLogin());

        if (byLogin.isPresent()){
            throw new UserAlreadyExistsException();
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        appUserRepository.save(appUser);
    }

    public PageResponse<AppUserHeaderVO> getPageAppUsers(int page){

        Page<AppUser> allBy = appUserRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));
        long totalElements = allBy.getTotalElements();
        int totalPages = allBy.getPageable().getPageNumber();
        List<AppUserHeaderVO> collect = allBy.getContent().stream().map(
                 element -> new AppUserHeaderVO(element.getId(), element.getLogin(), element.getName(),
                 element.getSurname())).collect(Collectors.toList());
        return new PageResponse<>(totalElements,totalPages,collect);
    }

    public PageResponse<AppUserHeaderVO> getAllAppUsers(){
        return getPageAppUsers(0);
    }

    public AppUserVO getUser(long id) throws UserDoesNotExistsOrIsNotLoggedInException{
        Optional<AppUser> byId = appUserRepository.findById(id);
        if (byId.isPresent()){
            AppUser appUser = byId.get();

            List<IncidentHeaderVO> assignedTo = appUser.getAssignedToIncidentsList().stream().map(
                    element -> new IncidentHeaderVO(element.getId(), element.getTitle(), element.getDescription()
                            , element.getCreationDate(), element.getStatus())).collect(Collectors.toList());

            List<IncidentHeaderVO> createdBy = appUser.getCreatedIncidentsList().stream().map(
                    element -> new IncidentHeaderVO(element.getId(), element.getTitle(), element.getDescription()
                            , element.getCreationDate(), element.getStatus())).collect(Collectors.toList());

            AppUserVO appUserVO = new AppUserVO(appUser.getId(),appUser.getLogin(),appUser.getName(),appUser.getSurname(),
                    appUser.getUserRoleSet(),createdBy,assignedTo);

            return appUserVO;
        }

         throw new UserDoesNotExistsOrIsNotLoggedInException();
    }

    @Override
    public ListResponse<AppUserHeaderVO> getAllAppUsersAsList() {
        List<AppUser> all = appUserRepository.findAll();
        List<AppUserHeaderVO> collect = all.stream().map(appUser -> new AppUserHeaderVO(appUser.getId(), appUser.getLogin(), appUser.getName(),
                appUser.getSurname())).collect(Collectors.toList());

        return new ListResponse<>(collect);
    }

    public void removeUser(Long userId)throws UserDoesNotExistsOrIsNotLoggedInException{
        Optional<AppUser> byId = appUserRepository.findById(userId);
        if (byId.isPresent()){
            appUserRepository.delete(byId.get());
        }
        else{
            throw new UserDoesNotExistsOrIsNotLoggedInException();
        }
    }
}
