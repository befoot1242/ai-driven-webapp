// @trace DD-notice-001
package com.example.app.notice;

import java.time.OffsetDateTime;

public record Notice(long id, String title, OffsetDateTime publishedAt) {}
