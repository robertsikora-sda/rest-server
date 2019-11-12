package com.sda.repositories;

import com.sda.entities.User;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository {

    Stream<User> listAll();

    Optional<User> getById(Integer id);

    User save(User user);

    void delete(Integer id);

}
