// @trace TS-001
package com.example.app.notice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("TS-001 お知らせ一覧（NoticeService）")
class NoticeServiceTest {

    @Mock NoticeRepository repository;
    @InjectMocks NoticeService service;

    final OffsetDateTime now = OffsetDateTime.parse("2026-11-22T12:00:00+09:00");

    @Test
    @DisplayName("No.1 異常: page が負なら例外")
    void negativePage() {
        assertThatThrownBy(() -> service.list(-1, 20, now)).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 101})
    @DisplayName("No.2 境界: size が範囲外なら例外")
    void sizeOutOfRange(int size) {
        assertThatThrownBy(() -> service.list(0, size, now)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("No.3 正常: Repository の結果と件数を返す")
    void list() {
        var items = List.of(
            new Notice(2, "B", now.minusDays(1)),
            new Notice(1, "A", now.minusDays(2)));
        when(repository.findPublished(any(), anyInt(), anyLong())).thenReturn(items);
        when(repository.countPublished(any())).thenReturn(2L);

        var page = service.list(0, 20, now);

        assertThat(page.items()).containsExactlyElementsOf(items);
        assertThat(page.total()).isEqualTo(2);
    }
}
