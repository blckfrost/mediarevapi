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
            SELECT username, uid, password,  email, accountType, picturePath,
            CASE accountType
                WHEN 0 THEN 'Admin'
                WHEN 1 THEN 'DATA Entry'
                WHEN 2 THEN 'Tenant'
                WHEN 3 THEN 'Tenant User'
            END AS actType
            FROM mr_authUser WHERE email = ?
        """;
        try{
            User user = jdbcTemplate.queryForObject(query, new Object[]{email},
                    (rs,rowNum)->{
                        User u = new User();
                        u.setUsername(rs.getString("username"));
                        u.setEmail(rs.getString("email"));
                        u.setUid(rs.getString("uid"));
                        u.setPassword(rs.getString("password"));
                        u.setAccountType(rs.getInt("accountType"));
                        u.setPicturePath(rs.getString("picturePath"));
                        u.setAcType(rs.getString("acType"));
                        return u;
                    });
            assert user != null;
            return Optional.of(user);
        }catch(EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
