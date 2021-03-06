package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserVO;

public interface IAppUserService {
    void register(AppUserDTO appUserDTO) throws UserAlreadyExistsException;
    PageResponse<AppUserHeaderVO> getPageAppUsers(int page);
    PageResponse<AppUserHeaderVO> getAllAppUsers();
    void removeUser(Long userId)throws UserDoesNotExistsOrIsNotLoggedInException;
    AppUserVO getUser(long id) throws UserDoesNotExistsOrIsNotLoggedInException;
    ListResponse<AppUserHeaderVO> getAllAppUsersAsList();
}
