package com.sda.controllers.api;

import com.sda.entities.User;
import com.sda.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Random;
import java.util.stream.Stream;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
class UserControllerApi {

    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Stream<User>> getAll() {
        return ResponseEntity.ok(userService.listAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOne(@PathVariable Integer id) {
        return userService.getUserById(id).map(ResponseEntity::ok
        ).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity update(@RequestBody User updateUser, @PathVariable Integer id) {
        return userService.getUserById(id)
                .map(t -> {
                    updateUser.setId(id);
                    userService.saveUser(updateUser);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        User createdUser = userService.saveUser(user);
        return ResponseEntity.created(URI.create(String.format("/users/%s", createdUser.getId()))).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/count")
    public ResponseEntity<Long> usersCount() {
        final long count = userService.listAllUsers().count();
        final int random = new Random().nextInt();
        if(random%2 == 0) {
            return ResponseEntity.ok(count);
        }
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }

}
