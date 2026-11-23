// @trace DD-notice-001
package com.example.app.notice;

import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeRepository {

    private final JdbcClient jdbc;

    public NoticeRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    public List<Notice> findPublished(OffsetDateTime now, int limit, long offset) {
        return jdbc.sql("""
                SELECT id, title, published_at
                  FROM notices
                 WHERE published_at <= :now
                 ORDER BY published_at DESC, id DESC
                 LIMIT :limit OFFSET :offset
                """)
            .param("now", now)
            .param("limit", limit)
            .param("offset", offset)
            .query((rs, i) -> new Notice(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getObject("published_at", OffsetDateTime.class)))
            .list();
    }

    public long countPublished(OffsetDateTime now) {
        return jdbc.sql("SELECT count(*) FROM notices WHERE published_at <= :now")
            .param("now", now)
            .query(Long.class)
            .single();
    }
}
