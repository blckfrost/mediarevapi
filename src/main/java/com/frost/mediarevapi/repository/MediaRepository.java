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
            SELECT m.pkid, m.title, m.mID, m.mediaType, m.createDate, m.updateDate, m.status,
                            CASE m.mediaType
                                WHEN 1 THEN 'Print Media'
                                WHEN 2 THEN 'Radio Story'
                                WHEN 3 THEN 'TV media'
                                WHEN 4 THEN 'Web Media'
                            END AS typeIW
                            FROM medias m
                            ORDER BY pkid DESC
        """;
        try{
            return jdbcTemplate.query(query, (rs,rowNum) ->{
                Media media = new Media();

                media.setPkid(rs.getLong("pkid"));
                media.setTitle(rs.getString("title"));
                media.setMID(rs.getString("mID"));
                media.setMediaType(rs.getInt("mediaType"));
                media.setCreateDate(rs.getTimestamp("createDate"));
                media.setUpdateDate(rs.getTimestamp("updateDate"));
                media.setStatus(rs.getString("status"));
                media.setTypeIW(rs.getString("typeIW"));

                return media;
            });
        }
        catch (Exception e){
            return  null;
        }
    }

    public int insertMedia(Media media) {
        String sql = """
        INSERT INTO medias (title, mID, mediaType, createDate, updateDate, status)
        VALUES (?,?,?,?,?,?)
        """;
        return jdbcTemplate.update(sql,
                media.getTitle(),
                media.getMID(),
                media.getMediaType(),
                media.getCreateDate(),
                media.getUpdateDate(),
                media.getStatus()
        );
    }

    public int insertPrintMedia(PrintMedia printMedia) {
        String sql = """
         INSERT INTO printMedias
                    (mID, title, author, publication, pageNumber, articleSummary, keywords, articleText, picturePath, industry, subindustry, createDate)
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        return jdbcTemplate.update(sql,
                printMedia.getMID(),
                printMedia.getTitle(),
                printMedia.getAuthor(),
                printMedia.getPublication(),
                printMedia.getPageNumber(),
                printMedia.getArticleSummary(),
                printMedia.getKeywords(),
                printMedia.getArticleText(),
                printMedia.getPicturePath(),
                printMedia.getIndustry(),
                printMedia.getSubindustry(),
                printMedia.getCreateDate()
        );
    }
}
