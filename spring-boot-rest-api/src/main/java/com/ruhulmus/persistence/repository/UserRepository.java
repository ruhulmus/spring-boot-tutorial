package com.ruhulmus.persistence.repository;

import com.ruhulmus.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

}
