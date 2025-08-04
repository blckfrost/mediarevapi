package com.frost.mediarevapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintMedia {
    private Long pkid;
    private String mID;             // Foreign key to mr_media.mID
    private String title;
    private String author;
    private String publication;
    private String pageNumber;
    private String articleSummary;
    private String keywords;
    private String articleText;
    private String picturePath;
    private String industry;
    private String subindustry;
    private Timestamp createDate;
}
