package com.example.cinema.repository;

import org.springframework.stereotype.Repository;
import com.example.cinema.model.Role;

@Repository
public interface RoleRepository extends GenericRepository<Role>{

    Role getByTitle(String title);
}
