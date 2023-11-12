package ru.pio.aclij.taskmanagerapi.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pio.aclij.taskmanagerapi.security.entities.Role;
import ru.pio.aclij.taskmanagerapi.security.exceptions.DefaultRoleNotFoundException;
import ru.pio.aclij.taskmanagerapi.security.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleServiceProperties properties;

    public Role getDefaultRole() {
        System.out.println(properties.getDefaultRole());
        System.out.println(properties.getDefaultRole().length());
        return roleRepository.findByName(properties.getDefaultRole())
                .orElseThrow(() -> new DefaultRoleNotFoundException("Default role not found. user.service:default_role"));
    }
}
