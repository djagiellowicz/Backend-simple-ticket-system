package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserAlreadyExistsException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.exceptions.UserDoesNotExistsOrIsNotLoggedInException;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.info.AppUserDTO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ObjectResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.PageResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.Response;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserHeaderVO;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.valueobjects.AppUserVO;
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
    public ResponseEntity<PageResponse<AppUserHeaderVO>> list(){
        PageResponse<AppUserHeaderVO> listOfAppUsers = appUserService.getAllAppUsers();

        return ResponseFactory.pageResponse(listOfAppUsers);
    }

    @RequestMapping(path = "/list/{page}")
    public ResponseEntity<PageResponse<AppUserHeaderVO>> list(@PathVariable("page") int page){

        PageResponse<AppUserHeaderVO> listOfAppUsers = appUserService.getPageAppUsers(page);

        return ResponseFactory.pageResponse(listOfAppUsers);
    }

    @RequestMapping(path = "/get/{number}")
    public ResponseEntity<ObjectResponse<AppUserVO>> getUser(@PathVariable("number") long id){
        try {
            AppUserVO user = appUserService.getUser(id);
            return ResponseFactory.objectRespone((new ObjectResponse<>(user)));
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.objectResponeBad();
        }

    }

    @RequestMapping(path = "/delete/{userid}")
    public ResponseEntity<Response> deleteUser(@PathVariable ("userid") long userId){
        try {
            appUserService.removeUser(userId);
        } catch (UserDoesNotExistsOrIsNotLoggedInException e) {
            return ResponseFactory.badRequest();
        }
        return ResponseFactory.deleted();
    }
}
