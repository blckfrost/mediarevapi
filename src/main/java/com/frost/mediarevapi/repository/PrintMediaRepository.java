package com.frost.mediarevapi.repository;

import com.frost.mediarevapi.model.PrintMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrintMediaRepository extends JpaRepository<PrintMedia, Integer> {
    Optional<PrintMedia> findByMediaId(String mediaId);
}
