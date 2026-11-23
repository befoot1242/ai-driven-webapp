---
applyTo: "api/**"
---

# API 設計ルール（OpenAPI）

- `api/openapi.yaml` が API 契約の正本。フロントとバックはこれに従う。
- パスは複数形の名詞（`/api/notices`）。動詞は使わない。
- operationId は `動詞+名詞` の camelCase（`listNotices`, `getNotice`）。
- 各 operation の `description` に、対応する基本設計 ID を `@trace BD-xxx` として書く。
- エラー応答は `application/problem+json`（RFC 9457）で、`components/schemas/Problem` を参照する。
- 一覧取得はページング（`page`, `size`）を持たせる。上限値をスキーマに書く。
- 日時は ISO 8601 文字列（`format: date-time`）、タイムゾーン付き。
- 破壊的変更（項目の削除・型変更・必須化）をする場合は ADR を作成する。
