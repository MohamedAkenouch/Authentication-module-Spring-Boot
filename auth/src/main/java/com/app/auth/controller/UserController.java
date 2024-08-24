package com.app.auth.controller;

import com.app.auth.model.User;
import com.app.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER') and hasPermission(#id, 'READ')")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') and hasPermission(#id, 'EDIT')")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }
}
