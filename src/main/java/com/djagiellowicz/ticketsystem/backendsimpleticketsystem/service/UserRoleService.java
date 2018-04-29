package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.service;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.DTO.response.ListResponse;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.UserRoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public ListResponse<UserRole> getUserRoles(){
        List<UserRole> allRoles = userRoleRepository.findAll();
        return new ListResponse<>(allRoles);
    }

}
