package com.ies.repositories;

import com.ies.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

        public User findByEmail(String email);
}
