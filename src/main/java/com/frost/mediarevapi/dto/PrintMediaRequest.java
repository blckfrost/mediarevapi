package com.frost.mediarevapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrintMediaRequest {
    private String title;
    private String author;
    private Integer pageNumber;
    private String publication;
    private String issueNumber;
    private String summary;
    private String keywords;
    private String content;
    private String imagePath;
    private String industry;
    private String subIndustry;
}
