package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void register(AppUser appUser) throws UserAlreadyExistsException {
        appUser.setLogin(appUser.getLogin().toLowerCase());

        Optional<AppUser> byLogin = appUserRepository.findByLogin(appUser.getLogin());

        if (byLogin.isPresent()){
            throw new UserAlreadyExistsException();
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        appUserRepository.save(appUser);
    }

    public PageResponse<AppUser> getPageAppUsers(int page){
        Page<AppUser> allBy = appUserRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));

        return new PageResponse<>(allBy);
    }

    public PageResponse<AppUser> getAllAppUsers(){
        return getPageAppUsers(0);
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
