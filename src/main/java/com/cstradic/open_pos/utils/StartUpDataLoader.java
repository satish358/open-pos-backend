package com.cstradic.open_pos.utils;

import com.cstradic.open_pos.models.UserRole;
import com.cstradic.open_pos.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StartUpDataLoader implements ApplicationRunner {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        loadUserRoles();
    }

    public void loadUserRoles() {
        if(!userRoleRepository.existsByNameIgnoreCase("ADMIN")) {
            List<UserRole> userRoles = new ArrayList<>();
            userRoles.add(new UserRole(null, "ADMIN"));
            userRoles.add(new UserRole(null, "WORKER"));
            userRoles.add(new UserRole(null, "MANAGER"));
            userRoles.add(new UserRole(null, "CUSTOMER"));

            userRoleRepository.saveAll(userRoles);
        }
    }
}
