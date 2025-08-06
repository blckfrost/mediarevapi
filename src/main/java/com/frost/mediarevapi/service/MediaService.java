package com.frost.mediarevapi.service;

import com.frost.mediarevapi.model.Media;
import com.frost.mediarevapi.model.PrintMedia;
import com.frost.mediarevapi.repository.MediaRepository;
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

    public List<Media> getAllMedia() {
        return mediaRepository.findAllMedia();
    }

    public Map<String, Object> getMediaByIdAndToken(String id, String utk){
        if(id == null || utk == null){
            throw new IllegalArgumentException("Invalid parameters");
        }

        String queryMediaType = "SELECT media_type FROM media WHERE media_id = ?";

        Integer mediaType;
        try {
            mediaType = jdbcTemplate.queryForObject(queryMediaType, new Object[]{id}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Media Not Found");
        }


        if(mediaType == null){
            throw new RuntimeException("Media Not Found");
        }

        String detailQuery = switch (mediaType){
            case 1 -> "SELECT * FROM print_medias WHERE media_id = ?";
            case 2 -> "SELECT * FROM radio_stories WHERE media_id = ?";
            case 3 -> "SELECT * FROM tv_stories WHERE media_id = ?";
            case 4 -> "SELECT * FROM web_medias WHERE media_id = ?";
            default -> throw new RuntimeException("Unknown mediaType");
        };

        List<Map<String, Object>> mediaData = jdbcTemplate.queryForList(detailQuery, id);
        if(mediaData.isEmpty()){
            throw new RuntimeException("No media detail found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data",mediaData.get(0));
        response.put("type",mediaType);

        return response;
    }

    public void createPrintMedia(Media media, PrintMedia printMedia){
        mediaRepository.insertMedia(media);

        mediaRepository.insertPrintMedia(printMedia);

    }


}
