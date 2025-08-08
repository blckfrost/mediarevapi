package com.frost.mediarevapi.repository;

import com.frost.mediarevapi.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Integer> {
    Optional<Media> findMediaById(String mediaId);
}