package com.ruhulmus.service.impl;
import com.ruhulmus.dto.user.request.UserDto;
import com.ruhulmus.persistence.entity.User;
import com.ruhulmus.persistence.repository.UserRepository;
import com.ruhulmus.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

  @Override
  public UserDto add(UserDto userDto){
      User user = new User();
      user.setFullName(userDto.getFullName());
      user.setUserName(userDto.getUserName());
      user.setEmailAddress(userDto.getEmailAddress());
      user.setPhoneNumber(userDto.getPhoneNumber());
      user.setAddress(userDto.getAddress());

      userRepository.save(user);
      return userDto;
  }
}
