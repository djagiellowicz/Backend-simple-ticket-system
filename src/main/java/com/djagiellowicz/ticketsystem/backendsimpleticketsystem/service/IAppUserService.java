package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;

public interface IAppUserService {
    void register(AppUserDTO appUserDTO) throws UserAlreadyExistsException;
    PageResponse<AppUserDTO> getPageAppUsers(int page);
    PageResponse<AppUserDTO> getAllAppUsers();
    void removeUser(Long userId)throws UserDoesNotExistsOrIsNotLoggedInException;
}
