package com.frost.mediarevapi.service;

import com.frost.mediarevapi.model.Media;
import com.frost.mediarevapi.model.PrintMedia;
import com.frost.mediarevapi.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

        String queryMediaType = "SELECT mediaType FROM medias WHERE mId = ?";
        Integer mediaType = jdbcTemplate.queryForObject(queryMediaType, new Object[]{id}, Integer.class);

        if(mediaType == null){
            throw new RuntimeException("Media Not Found");
        }

        String detailQuery = switch (mediaType){
            case 1 -> "SELECT * FROM printMedias WHERE mid = ?";
            case 2 -> "SELECT * FROM radioStories WHERE mid = ?";
            case 3 -> "SELECT * FROM tvStories WHERE mid = ?";
            case 4 -> "SELECT * FROM webMedias WHERE mid = ?";
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
