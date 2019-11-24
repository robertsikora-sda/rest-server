package com.sda.services;

import com.sda.entities.User;
import com.sda.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Stream<User> listAllUsers() {
        return userRepository.listAll();
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.delete(id);
    }

}
