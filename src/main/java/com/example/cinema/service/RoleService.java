package com.example.cinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cinema.model.Role;
import com.example.cinema.repository.RoleRepository;

@Service
public class RoleService {

    private RoleRepository roleRepository;


    public Role getByTitle(String title) {
        return roleRepository.getByTitle(title);
    }

    public Role create(Role newRole) {
        Role existingRole = getByTitle(newRole.getTitle());
        return existingRole != null
                ? existingRole
                : roleRepository.save(newRole);
    }


    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
