package com.frost.mediarevapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private Integer id;
    private String title;
    private String mediaId;
    private Integer mediaType;
    private Integer status;
    private String createdBy;
    private String updatedBy;
}
