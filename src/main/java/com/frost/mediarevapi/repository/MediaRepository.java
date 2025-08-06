package com.frost.mediarevapi.repository;

import com.frost.mediarevapi.model.Media;
import com.frost.mediarevapi.model.PrintMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MediaRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Media> findAllMedia() {
        String query = """
            SELECT id, title, media_id, media_type, created_by, updated_by, status
            FROM media
            ORDER BY id DESC
        """;
        try{
            return jdbcTemplate.query(query, (rs,rowNum) ->{
                Media media = new Media();

                media.setId(rs.getInt("id"));
                media.setTitle(rs.getString("title"));
                media.setMediaId(rs.getString("media_id"));
                media.setMediaType(rs.getInt("media_type"));
                media.setCreatedBy(rs.getString("created_by"));
                media.setUpdatedBy(rs.getString("updated_by"));
                media.setStatus(rs.getInt("status"));

                return media;
            });
        }
        catch (Exception e){
            e.printStackTrace();
            return List.of();
        }
    }

    public int insertMedia(Media media) {
        String sql = """
        INSERT INTO media (title, media_id, media_type, created_by, updated_by, status)
        VALUES (?,?,?,?,?,?)
        """;
        return jdbcTemplate.update(sql,
                media.getTitle(),
                media.getMediaId(),
                media.getMediaType(),
                media.getCreatedBy(),
                media.getUpdatedBy(),
                media.getStatus()
        );
    }

    public int insertPrintMedia(PrintMedia printMedia) {
        String sql = """
         INSERT INTO print_medias
                    (title, author, page_number, publication, issue_number, summary, keywords, content, image_path, industry, sub_industry, media_id)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                printMedia.getTitle(),
                printMedia.getAuthor(),
                printMedia.getPageNumber(),
                printMedia.getPublication(),
                printMedia.getIssueNumber(),
                printMedia.getSummary(),
                printMedia.getKeywords(),
                printMedia.getContent(),
                printMedia.getPicturePath(),
                printMedia.getIndustry(),
                printMedia.getSubIndustry(),
                printMedia.getMediaId()
        );
    }
}
