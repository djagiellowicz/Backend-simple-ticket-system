package com.djagiellowicz.ticketsystem.backendsimpleticketsystem.dataInitialiser;

import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.AppUser;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.model.UserRole;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.AppUserRepository;
import com.djagiellowicz.ticketsystem.backendsimpleticketsystem.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class DataInitializer {

    private UserRoleRepository userRoleRepository;
    private AppUserRepository appUserRepository;

    @Autowired
    public DataInitializer(UserRoleRepository userRoleRepository, AppUserRepository appUserRepository) {
        this.userRoleRepository = userRoleRepository;
        this.appUserRepository = appUserRepository;
    }


    private void initialise() {
        Optional<UserRole> admin = userRoleRepository.findUserRoleByName("ADMIN");
        Optional<UserRole> user = userRoleRepository.findUserRoleByName("USER");
        Optional<UserRole> operator = userRoleRepository.findUserRoleByName("OPERATOR");
        Optional<AppUser> adminUser = appUserRepository.findByLogin("admin");

        if (!admin.isPresent()){
            userRoleRepository.save(new UserRole("ADMIN"));
        }
        if (!user.isPresent()){
            userRoleRepository.save(new UserRole("USER"));
        }
        if (!operator.isPresent()){
            userRoleRepository.save(new UserRole("OPERATOR"));
        }
        if (!adminUser.isPresent()){
            appUserRepository.save(new AppUser("admin","123456", admin.get()));
        }
    }
}
