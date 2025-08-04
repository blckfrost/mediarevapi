package com.frost.mediarevapi.repository;

import com.frost.mediarevapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<User> findUserByEmail(String email) {
        String query = """
            SELECT username, uid, password, email, accountType, picturePath, status
            FROM users WHERE email = ?
        """;
        try {
            User user = jdbcTemplate.queryForObject(query, new Object[]{email},
                    (rs, rowNum) -> {
                        User u = new User();
                        u.setUsername(rs.getString("username"));
                        u.setEmail(rs.getString("email"));
                        u.setUid(rs.getString("uid"));
                        u.setPassword(rs.getString("password"));
                        u.setAccountType(rs.getInt("accountType"));
                        u.setPicturePath(rs.getString("picturePath"));
                        u.setStatus(rs.getString("status"));
                        return u;
                    });
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        String query = """
            INSERT INTO users (uid, username, email, password, accountType, picturePath)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(query,
                user.getUid(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getAccountType(),
                user.getPicturePath()
        );

        return user;
    }
}
