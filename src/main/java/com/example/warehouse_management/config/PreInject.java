package com.example.warehouse_management.config;


import com.example.warehouse_management.entity.Role;
import com.example.warehouse_management.entity.User;
import com.example.warehouse_management.entity.enumirated.UserStatus;
import com.example.warehouse_management.repository.RoleRepository;
import com.example.warehouse_management.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PreInject {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;


    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private Role createRole(String name, String description) {
        return Role.builder()
                .name(name)
                .description(description)
                .build();
    }

    @PostConstruct
    @Transactional
    public void setDefaultUsers() {
        if (roleRepository.count() == 0) {
            List<Role> roles = new ArrayList<>();
            roles.add(createRole("ROLE_ADMIN", "Admin"));
            roles.add(createRole("ROLE_USER", "User"));
            roleRepository.saveAll(roles);
        }

        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("admin");

            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
            user.setRoles(Set.of(roleAdmin));
            user.setFirstname("admin");
            user.setLastname("admin");

            user.setUserStatus(UserStatus.ACTIVE);
            user.setPassword(encodePassword("123"));
            userRepository.save(user);
        }

    }
}
