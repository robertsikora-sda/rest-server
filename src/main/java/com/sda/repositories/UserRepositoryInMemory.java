package com.sda.repositories;

import com.sda.entities.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Repository
public class UserRepositoryInMemory implements UserRepository {

    private AtomicInteger idGenerator = new AtomicInteger();
    private Map<Integer, User> users = new HashMap<>();

    @Override
    public Stream<User> listAll() {
        return users.values().stream();
    }

    @Override
    public Optional<User> getById(Integer id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public User save(User user) {
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return users.put(user.getId(), user);
    }

    @Override
    public void delete(Integer id) {
        users.remove(id);
    }
}
