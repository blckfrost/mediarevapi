package com.frost.mediarevapi.service;

import com.frost.mediarevapi.model.Media;
import com.frost.mediarevapi.model.PrintMedia;
import com.frost.mediarevapi.repository.MediaRepository;
import com.frost.mediarevapi.repository.PrintMediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private PrintMediaRepository printMediaRepository;

    public List<Media> getAllMedia() {
        return mediaRepository.findAll();
    }

    public Map<String, Object> getMediaById(String mediaId){
        Media media = mediaRepository.findMediaById(mediaId)
                .orElseThrow(() -> new RuntimeException("Media not found"));

        int mediaType = media.getMediaType();

        Map<Object, Object> response = new HashMap<>();
        response.put("mediaType", mediaType);

        switch (mediaType) {
            case 1 -> {
                PrintMedia printMedia = printMediaRepository.findByMediaId(mediaId)
                        .orElseThrow(() -> new RuntimeException("PrintMedia not found"));
                response.put("data", printMedia);
            }
            case 2 -> {

            }
            default -> {
                throw new RuntimeException("Invalid media type");
            }
        }

        return response;
    }

    public void createPrintMedia(Media media, PrintMedia printMedia){
        mediaRepository.save(media);
        printMediaRepository.save(printMedia);
    }
}
