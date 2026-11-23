# トレーサビリティ表

> このファイルは `npm run trace` で自動生成されます。手で編集しないでください。

| 要件 | 基本設計 | 詳細設計 | テスト設計 | 実装 | テストコード |
|---|---|---|---|---|---|
| FR-001 (approved) （サンプル）お知らせ一覧の表示 | BD-001 (approved) | DD-notice-001 (review) | TS-001 (draft) | `api/openapi.yaml`<br>`backend/src/main/java/com/example/app/notice/Notice.java`<br>`backend/src/main/java/com/example/app/notice/NoticeController.java`<br>`backend/src/main/java/com/example/app/notice/NoticePage.java`<br>`backend/src/main/java/com/example/app/notice/NoticeRepository.java`<br>`backend/src/main/java/com/example/app/notice/NoticeService.java`<br>`backend/src/main/resources/db/migration/V1__create_notices.sql`<br>`frontend/src/api/notices.ts`<br>`frontend/src/stores/notice.ts`<br>`frontend/src/views/NoticeListView.vue` | `backend/src/test/java/com/example/app/notice/NoticeApiIntegrationTest.java`<br>`backend/src/test/java/com/example/app/notice/NoticeServiceTest.java`<br>`e2e/tests/notice-list.spec.ts`<br>`frontend/src/stores/notice.test.ts` |

## 検出された問題（2 件）

- 未承認の設計に基づくコード/テストがあります: DD-notice-001(review) ← backend/src/main/java/com/example/app/notice/Notice.java, backend/src/main/java/com/example/app/notice/NoticeController.java, backend/src/main/java/com/example/app/notice/NoticePage.java, backend/src/main/java/com/example/app/notice/NoticeRepository.java, backend/src/main/java/com/example/app/notice/NoticeService.java, backend/src/main/resources/db/migration/V1__create_notices.sql, frontend/src/api/notices.ts, frontend/src/stores/notice.ts, frontend/src/views/NoticeListView.vue
- 未承認の設計に基づくコード/テストがあります: TS-001(draft) ← backend/src/test/java/com/example/app/notice/NoticeApiIntegrationTest.java, backend/src/test/java/com/example/app/notice/NoticeServiceTest.java, e2e/tests/notice-list.spec.ts, frontend/src/stores/notice.test.ts
