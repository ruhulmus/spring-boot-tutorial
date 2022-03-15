package com.ruhulmus.service.contract;

import com.ruhulmus.dto.user.request.UserDto;
import com.ruhulmus.persistence.entity.User;

import java.util.List;

public interface UserService {
    UserDto add(UserDto userDto);
    List<User> list();
}
