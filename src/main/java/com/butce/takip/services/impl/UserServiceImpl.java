package com.butce.takip.services.impl;

import org.springframework.stereotype.Service;

import com.butce.takip.models.User;
import com.butce.takip.repositories.UserRepository;
import com.butce.takip.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username).orElse(null);
  }

}
