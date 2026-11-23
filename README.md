# ai-driven-webapp

要件定義からテストまでを AI エージェント（主に GitHub Copilot）と一気通貫で進めるための、Web アプリケーションのひな形です。

**構成**: Vue 3 + TypeScript + Pinia（画面）／ Spring Boot 3 + Java 21（API）／ PostgreSQL + Flyway ／ OpenAPI（API 契約）／ AWS ECS + RDS（AWS CDK）

## どこに何があるか

| 何を | どこに |
|---|---|
| AI 向けの地図・工程ルール | `AGENTS.md` |
| 全工程共通の前提 | `docs/00_steering/` |
| 要件（機能 / 非機能） | `docs/10_requirements/` |
| 基本設計 / 詳細設計 / テスト設計 | `docs/20_〜40_` |
| 設計判断 | `docs/80_decisions/`（ADR） |
| 外部参照ドキュメント | `docs/90_references/` |
| 実装ルール（フロント / バック / API / DB）・テストルール・IaC ルール | `.github/instructions/` |
| 工程ごとのプロンプト | `.github/prompts/` |
| API 契約（正本） | `api/openapi.yaml` |
| 画面 / API / E2E / IaC | `frontend/` `backend/` `e2e/` `infra/` |

## はじめかた

```bash
docker compose up -d                          # PostgreSQL
cd backend  && ./gradlew test && ./gradlew bootRun
cd frontend && npm install && npm run dev     # http://localhost:5173
cd e2e && npm install && npx playwright install chromium && npx playwright test
npm run trace                                 # リポジトリ直下
```

- バックエンドの結合テスト（Testcontainers）は `./gradlew integrationTest`（Docker が必要）。
- 初回の `./gradlew` は Gradle 本体をダウンロードします。社内プロキシ環境では `gradle.properties` にプロキシ設定が必要な場合があります。

## 開発の流れ（1 機能あたり）

| 工程 | プロンプト | 成果物 | 人の作業 |
|---|---|---|---|
| 1. 機能要件 | `/01-req-functional` | `FR-NNN` | レビューして `status: approved` |
| 2. 非機能要件 | `/02-req-non-functional` | `NFR-*` | 同上 |
| 3. 基本設計 | `/03-basic-design` | `BD-NNN`（+ ADR、OpenAPI 変更案） | 同上 |
| 4. 詳細設計 | `/04-detail-design` | `DD-<機能>-NNN` | 同上 |
| 5. テスト設計 | `/05-test-design` | `TS-NNN` | 同上 |
| 6. 実装・テスト | `/06-implement` | OpenAPI → DB → バック → フロント | コードレビュー |

- 各工程の後に `design-reviewer` エージェントでレビューさせると効率的です。
- **`status: approved` にするのは人だけ**です（`AGENTS.md` の承認ゲート）。
- `npm run trace` で `docs/traceability.md` が更新されます。Java・Vue・SQL 内の `@trace` も集計します。

## サンプルの削除

動作確認用に「お知らせ一覧」（`FR-001` → `BD-001` → `DD-notice-001` → `TS-001`、`notice` パッケージ、`V1__create_notices.sql`、`NoticeListView.vue` など）が入っています。
`DD-notice-001` は意図的に `review`（未承認）にしてあり、`npm run trace` で承認ゲート違反として検出されます。
本番の要件定義を始める前に、サンプル（「sample / サンプル」と付いたもの）を削除してください。

## 次にやること

1. `docs/00_steering/product.md` と `tech.md` の TODO を埋める
2. 認証方式・DB アクセス方式（JdbcClient / MyBatis / JPA）・画面の配信方式を ADR で決める
3. `/01-req-functional` で最初の要件を作る
