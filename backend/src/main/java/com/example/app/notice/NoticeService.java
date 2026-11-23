// @trace DD-notice-001
package com.example.app.notice;

import java.time.OffsetDateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoticeService {

    static final int MAX_SIZE = 100;

    private final NoticeRepository repository;

    public NoticeService(NoticeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public NoticePage list(int page, int size, OffsetDateTime now) {
        if (page < 0) {
            throw new IllegalArgumentException("page は 0 以上で指定してください");
        }
        if (size < 1 || size > MAX_SIZE) {
            throw new IllegalArgumentException("size は 1〜" + MAX_SIZE + " で指定してください");
        }
        var items = repository.findPublished(now, size, (long) page * size);
        var total = repository.countPublished(now);
        return new NoticePage(items, page, size, total);
    }
}
