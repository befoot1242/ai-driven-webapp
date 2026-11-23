---
id: DD-notice-001
title: （サンプル）お知らせ一覧の詳細設計
status: review
trace: [BD-001]
owner: "sample"
updated: 2026-11-22
---

# DD-notice-001 （サンプル）お知らせ一覧の詳細設計

## 1. 概要

BD-001 の SCR-01 / API-01 / notices を実装レベルまで具体化する。

## 2. モジュール構成

| ファイル | 役割 |
|---|---|
| `backend/.../notice/NoticeController.java` | `GET /api/notices` |
| `backend/.../notice/NoticeService.java` | ページ番号の検証、現在時刻での絞り込み |
| `backend/.../notice/NoticeRepository.java` | SELECT（JdbcClient） |
| `backend/.../db/migration/V1__create_notices.sql` | テーブル作成 |
| `frontend/src/api/notices.ts` | API 呼び出し |
| `frontend/src/stores/notice.ts` | 一覧の状態（Pinia） |
| `frontend/src/views/NoticeListView.vue` | 一覧画面 |

## 3. インターフェース

```java
public record Notice(long id, String title, OffsetDateTime publishedAt) {}
public record NoticePage(List<Notice> items, int page, int size, long total) {}
NoticePage NoticeService.list(int page, int size, OffsetDateTime now);
```

## 4. 処理詳細

1. `page` < 0 または `size` が 1〜100 の範囲外なら 400（Problem Details）
2. `published_at <= now` を `published_at DESC, id DESC` で取得（LIMIT/OFFSET）
3. 件数を別クエリで取得

## 5. 状態遷移（フロント）

`idle → loading → loaded | error`。ストア `notice` の action `fetch(page)` でのみ更新する。

## 6. エラー処理

| エラー | 検知方法 | ユーザーへの表示 | ログ |
|---|---|---|---|
| 不正なパラメータ | Service で検証 | （画面からは発生しない） | WARN |
| API 通信失敗 | fetch の失敗 / 5xx | 「お知らせを取得できませんでした」 | console.error |

## 7. ログ出力

API アクセスログのみ

## 8. テスト観点

- 正常系: 公開済みのみ・新しい順
- 異常系: page=-1、size=0、size=101
- 境界値: size=1、size=100、0 件

## 9. 未決事項

なし

## 変更履歴

| 日付 | 変更者 | 内容 |
|---|---|---|
| 2026-11-22 | sample | 初版 |
