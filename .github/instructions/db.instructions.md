---
applyTo: "backend/src/main/resources/db/**"
---

# DB ルール（PostgreSQL / Flyway）

- スキーマ変更はすべて Flyway のマイグレーション（`V<連番>__<内容>.sql`）で行う。
- **適用済みの `V*.sql` は編集しない。** 修正は新しいバージョンを追加する。
- ファイル先頭に `-- @trace DD-xxx` を書く。
- テーブル名・列名は snake_case、テーブル名は複数形。
- すべてのテーブルに主キー、`created_at` / `updated_at`（`timestamptz`）を持たせる。
- 外部キーには索引を作る。
- 列にはコメント（`COMMENT ON COLUMN`）を付け、基本設計のテーブル定義と一致させる。
- 大きなテーブルへの索引追加・列追加はロックの影響を ADR か詳細設計で検討してから行う。
