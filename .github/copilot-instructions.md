# Copilot リポジトリ共通指示

工程ルールとファイル配置はリポジトリ直下の `AGENTS.md` を正とする。必ず従うこと。

## 技術スタック

| 層 | 技術 |
|---|---|
| フロントエンド | Vue 3（Composition API, `<script setup>`）+ TypeScript strict + Vite + Vue Router + Pinia |
| バックエンド | Java 21 + Spring Boot 3 + Gradle（Kotlin DSL） |
| DB | PostgreSQL 16 + Flyway |
| API 契約 | OpenAPI 3.1（`api/openapi.yaml`） |
| テスト | Vitest（フロント単体）、JUnit 5 + Mockito（バック単体）、Testcontainers（バック結合）、Playwright（E2E） |
| 実行基盤 | AWS（ECS Fargate + ALB + RDS for PostgreSQL）、IaC は AWS CDK（TypeScript） |

## コマンド

- フロント: `cd frontend && npm run lint && npm run test && npm run build`
- バック: `cd backend && ./gradlew test`
- E2E: `cd e2e && npx playwright test`
- トレーサビリティ: リポジトリ直下で `npm run trace`

## 応答ルール

- 回答・設計書・コードコメントは日本語。識別子は英語。
- 不明点や上流文書との矛盾は推測で埋めずに質問する。
- 変更を提案するときは関係する上流 ID（FR/BD/DD/TS）を明記する。
