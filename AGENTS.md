# AGENTS.md

AI エージェント（GitHub Copilot / Claude Code / Kiro など）向けの「地図」と「工程ルール」です。ここは短く保ち、詳細は各ファイルに分けています。

## プロジェクト概要

- Web アプリケーション（Vue 3 SPA + Spring Boot REST API + PostgreSQL、AWS ECS で稼働）
- 製品の目的・スコープ: `docs/00_steering/product.md`
- 技術スタック: `docs/00_steering/tech.md`
- リポジトリ構成規約: `docs/00_steering/structure.md`
- 用語集: `docs/00_steering/glossary.md`

## どこに何があるか

| 種別 | 場所 | ID 接頭辞 |
|---|---|---|
| 機能要件 | `docs/10_requirements/functional/` | `FR-` |
| 非機能要件 | `docs/10_requirements/non-functional/` | `NFR-<分類>-` |
| 基本設計 | `docs/20_basic-design/` | `BD-` |
| 詳細設計 | `docs/30_detail-design/<機能名>/` | `DD-<機能名>-` |
| テスト設計 | `docs/40_test-design/` | `TS-` |
| 設計判断 (ADR) | `docs/80_decisions/` | `ADR-` |
| 外部参照ドキュメント | `docs/90_references/index.md` | `REF-` |
| トレーサビリティ表 | `docs/traceability.md`（自動生成） | - |
| **API 契約（正本）** | `api/openapi.yaml` | - |
| DB マイグレーション（正本） | `backend/src/main/resources/db/migration/` | - |
| 実装ルール | `.github/instructions/impl-*.instructions.md`, `api.instructions.md`, `db.instructions.md` | - |
| テストルール | `.github/instructions/test.instructions.md` | - |
| フロントエンド | `frontend/` | - |
| バックエンド | `backend/` | - |
| E2E テスト | `e2e/` | - |
| IaC | `infra/`（`infra/AGENTS.md` も参照） | - |

## 工程ルール（承認ゲート）

1. 工程は 要件 → 基本設計 → 詳細設計 → テスト設計 → 実装/テスト の順に進める。
2. すべての設計書は先頭に front matter（`id` / `status` / `trace`）を持つ（`.github/instructions/docs.instructions.md`）。
3. **上流文書の `status` が `approved` でない場合、それを元に下流文書やコードを作成してはならない。** 気づいたら止めて人間に承認を依頼する。
4. `status` を `approved` にするのは人間だけ。AI は `draft` → `review` まで。
5. **API はコントラクトファーストで進める**: 基本設計で API を決めたら、先に `api/openapi.yaml` を更新し、フロントとバックはそれに合わせて実装する。実装から OpenAPI を逆生成しない。
6. コード・テスト・マイグレーションには上流 ID を書く（`// @trace DD-xxx`、`-- @trace DD-xxx`、テストは `@trace TS-xxx`）。
7. 外部仕様に依存する判断は `docs/90_references/index.md` に参照元を追記する。本文は丸ごとコピーしない。
8. 作業後は `npm run trace`（リポジトリ直下）でトレーサビリティ表を更新する。

## コマンド

```bash
docker compose up -d                      # ローカル PostgreSQL
cd backend  && ./gradlew test bootRun      # API（http://localhost:8080）
cd frontend && npm install && npm run dev  # 画面（http://localhost:5173）
cd frontend && npm run lint && npm run test
cd e2e && npx playwright test               # E2E（API と画面を起動した状態で）
npm run trace                               # リポジトリ直下で実行
```

## やってはいけないこと

- `approved` でない設計を根拠にした実装
- `api/openapi.yaml` を更新せずに API の入出力を変えること
- 適用済みのマイグレーションファイル（`V*.sql`）の編集（必ず新しいバージョンを追加する）
- 秘密情報（パスワード、キー）をコードや設定ファイルに書くこと
- `infra/envs/prod/` の直接変更、実環境へのデプロイコマンドの実行
