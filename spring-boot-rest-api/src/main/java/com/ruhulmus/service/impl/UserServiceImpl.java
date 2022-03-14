package com.ruhulmus.service.impl;

import com.ruhulmus.dto.user.request.User;
import com.ruhulmus.persistence.repository.UserRepository;
import com.ruhulmus.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

  @Override
  public User add(User user){
        return userRepository.save(user);
  };
}
