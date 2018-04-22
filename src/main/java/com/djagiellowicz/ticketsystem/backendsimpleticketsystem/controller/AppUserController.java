package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.Response;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Response> registerUser(@RequestBody AppUserDTO appUserDTO){
        try {
            appUserService.register(appUserDTO);
        } catch (UserAlreadyExistsException e) {
            return ResponseFactory.usernameAlreadyExists();
        }
        return ResponseFactory.created();
    }

    @RequestMapping(path = "/list")
    public ResponseEntity<PageResponse<AppUserDTO>> list(){
        PageResponse<AppUserDTO> listOfAppUsers = appUserService.getAllAppUsers();

        return ResponseFactory.pageResponse(listOfAppUsers);
    }
}
