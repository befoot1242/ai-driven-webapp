// @trace DD-notice-001
package com.example.app.notice;

import java.time.Clock;
import java.time.OffsetDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** api/openapi.yaml の operationId: listNotices */
@RestController
@RequestMapping("/api/notices")
public class NoticeController {

    private final NoticeService service;
    private final Clock clock;

    public NoticeController(NoticeService service, Clock clock) {
        this.service = service;
        this.clock = clock;
    }

    @GetMapping
    public NoticePage list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.list(page, size, OffsetDateTime.now(clock));
    }
}
