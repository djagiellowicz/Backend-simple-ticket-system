package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.controller;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ResponseFactory;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/role/")
public class RoleController {

    private UserRoleService userRoleService;

    @Autowired
    public RoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @RequestMapping(path = "/list")
    public ResponseEntity<ListResponse<UserRole>> getRoles(){
        ListResponse<UserRole> userRoles = userRoleService.getUserRoles();

        return ResponseFactory.listResponse(userRoles);
    }
}
