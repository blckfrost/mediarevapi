package com.frost.mediarevapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Media {
    private Long pkid;
    private String title;
    private String mID;
    private Integer mediaType;
    private Date createDate;
    private Date updateDate;
    private String status;
    private String typeIW;
}
