package com.butce.takip.controllers;

import com.butce.takip.models.User;
import com.butce.takip.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.saveUser(user));
  }

  @GetMapping("/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
    return ResponseEntity.ok(userService.findByUsername(username));
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    return ResponseEntity.ok(userService.updateUser(id, user));
  }

  public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
    userService.deleteUser(userId);
    return ResponseEntity.ok("Kullanıcı başarıyla silindi. ");
  }

}
