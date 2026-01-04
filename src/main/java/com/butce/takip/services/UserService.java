package com.butce.takip.services;

import com.butce.takip.models.User;
import java.util.Optional;

public interface UserService {
  User saveUser(User user);

  User findByUsername(String username);
}
