// @trace TS-001
package com.example.app.notice;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Tag("integration")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("TS-001 お知らせ一覧 API（結合）")
class NoticeApiIntegrationTest {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @TestConfiguration
    static class FixedClock {
        @Bean
        @Primary
        Clock fixedClock() {
            return Clock.fixed(Instant.parse("2026-11-22T03:00:00Z"), ZoneId.of("Asia/Tokyo"));
        }
    }

    @Autowired TestRestTemplate rest;
    @Autowired JdbcClient jdbc;

    @Test
    @DisplayName("No.6 正常: 公開済みのみ新しい順に返す")
    void listPublishedOnly() {
        jdbc.sql("""
            INSERT INTO notices (title, published_at) VALUES
              ('old',    '2026-11-01T00:00:00+09:00'),
              ('new',    '2026-11-20T00:00:00+09:00'),
              ('future', '2026-12-01T00:00:00+09:00')
            """).update();

        var page = rest.getForObject("/api/notices", NoticePage.class);

        assertThat(page.items()).extracting(Notice::title).containsExactly("new", "old");
        assertThat(page.total()).isEqualTo(2);
    }
}
