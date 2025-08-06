package com.frost.mediarevapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintMedia {
    private Integer id;
    private String title;
    private String author;
    private String publication;
    private String pageNumber;
    private String summary;
    private String keywords;
    private String content;
    private String picturePath;
    private String industry;
    private String subIndustry;
    private String mediaId;
}
