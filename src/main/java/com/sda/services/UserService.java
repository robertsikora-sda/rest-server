package com.sda.services;

import com.sda.entities.User;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserService {

    Stream<User> listAllUsers();

    Optional<User> getUserById(Integer id);

    User saveUser(User user);

    void deleteUser(Integer id);

}
