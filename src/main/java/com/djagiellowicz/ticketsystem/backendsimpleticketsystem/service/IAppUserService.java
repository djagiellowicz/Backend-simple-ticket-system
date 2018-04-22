package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;

public interface IAppUserService {
    void register(AppUserDTO appUserDTO) throws UserAlreadyExistsException;
    PageResponse<AppUser> getPageAppUsers(int page);
    PageResponse<AppUser> getAllAppUsers();
    void removeUser(Long userId)throws UserDoesNotExistsOrIsNotLoggedInException;
}
