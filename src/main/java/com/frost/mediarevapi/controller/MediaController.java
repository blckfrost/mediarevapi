package com.frost.mediarevapi.controller;

import com.frost.mediarevapi.model.Media;
import com.frost.mediarevapi.model.PrintMedia;
import com.frost.mediarevapi.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @GetMapping
    public ResponseEntity<?>getAllMedia(){
        try{
            List<Media> mediaList = mediaService.getAllMedia();
            return ResponseEntity.ok(Map.of("data", mediaList));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "something went wrong", "error", e.getMessage()));
        }
    }


    @PostMapping("/print")
    public ResponseEntity<?> createPrintMedia(
            @RequestBody Map<String, Object> payload
    ){
        try{
            var auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Authentication required"));
            }
            String userId = (String) auth.getPrincipal();

            String generatedMediaId = UUID.randomUUID().toString();

            Media media = new Media(
                    null,
                    (String) payload.get("title"),
                    generatedMediaId,
                    1,
                    1,
                    userId,
                    userId
            );

            PrintMedia printMedia = new PrintMedia(
                    null,
                    (String) payload.get("title"),
                    (String) payload.get("author"),
                    (String) payload.get("publication"),
                    (Integer) payload.get("page_number"),
                    (String) payload.get("summary"),
                    (String) payload.get("keywords"),
                    (String) payload.get("content"),
                    (String) payload.get("image_path"),
                    (String) payload.get("industry"),
                    (String) payload.get("sub_industry"),
                    generatedMediaId
            );

            mediaService.createPrintMedia(media, printMedia);

            return ResponseEntity.ok(Map.of(
                    "message", "Print media created successfully",
                    "media_id", generatedMediaId,
                    "created_by", userId
            ));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of(
                            "message", "Error creating media",
                            "error", e.getMessage()
                    ));
        }
    }
}
