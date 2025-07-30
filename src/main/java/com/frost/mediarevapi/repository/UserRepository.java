package com.frost.mediarevapi.repository;

import com.frost.mediarevapi.model.User;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findUserByEmail(String email);
}
