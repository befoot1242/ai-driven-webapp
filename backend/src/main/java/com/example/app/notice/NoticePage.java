// @trace DD-notice-001
package com.example.app.notice;

import java.util.List;

public record NoticePage(List<Notice> items, int page, int size, long total) {}
