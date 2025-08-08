package com.frost.mediarevapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "print_medias")
public class PrintMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String author;

    @Column(name = "page_number")
    private Integer pageNumber;

    @Column(name = "publication")
    private String publication;

    @Column(name = "issue_number")
    private String issueNumber;

    private String summary;
    private String keywords;
    private String content;

    @Column(name = "image_path")
    private String imagePath;

    private String industry;

    @Column(name = "sub_industry")
    private String subIndustry;

    @Column(name = "media_id")
    private String mediaId;
}
