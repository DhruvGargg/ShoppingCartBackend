package com.app.shoppingcartbackend.data;

import com.app.shoppingcartbackend.model.Role;
import com.app.shoppingcartbackend.model.User;
import com.app.shoppingcartbackend.repository.RoleRepository;
import com.app.shoppingcartbackend.repository.user.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultRolesIfNotExist();
        createDefaultUserIfNotExists();
    }

    private void createDefaultRolesIfNotExist() {
        Set.of("ROLE_USER", "ROLE_ADMIN").forEach(roleName -> {
            if (roleRepository.findByName(roleName).isEmpty()) {
                Role role = new Role(roleName);
                roleRepository.save(role);
                System.out.println("Role " + roleName + " initialized.");
            }
        });
    }

    private void createDefaultUserIfNotExists() {
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElse(null);

        for(int i = 1; i <= 5; i++) {
            String defaultEmail = "user" + i + "@example.com";
            if(userRepository.existsByEmail(defaultEmail)) continue;
            User user = new User();
            user.setFirstName("The User");
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("123456"));
            if (i == 5) {
                user.setRoles(Set.of(userRole, adminRole));
            } else {
                user.setRoles(Set.of(userRole));
            }
            userRepository.save(user);
            System.out.println("User " + i + " created successfully.");
        }
    }
}
