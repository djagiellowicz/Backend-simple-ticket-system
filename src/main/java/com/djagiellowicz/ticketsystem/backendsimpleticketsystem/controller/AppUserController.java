package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.Response;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
    public ResponseEntity<PageResponse<AppUser>> list(){
        PageResponse<AppUser> listOfAppUsers = appUserService.getAllAppUsers();

        return ResponseFactory.pageResponse(listOfAppUsers);
    }

//    @RequestMapping(path = "/get/{number}")
//    public ResponseEntity<AppUserDTO> get(@PathVariable("number") Long id){
//        appUserService.getUser(Long id);
//
//        return new ResponseEntity<AppUserDTO>(user));
//    }

    @RequestMapping(path = "/delete")
    public ResponseEntity<Response> deleteUser(long userId, long userToDeleteId){
        try {
            appUserService.removeUser(userToDeleteId);
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        }

        return ResponseFactory.deleted();
    }
}
