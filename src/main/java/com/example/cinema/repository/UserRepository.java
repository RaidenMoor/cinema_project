package com.example.cinema.repository;

import org.springframework.stereotype.Repository;
import com.example.cinema.model.User;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User getByLogin(String login);

    User getByEmail(String email);

    User getByChangePasswordToken(String uuid);
}
