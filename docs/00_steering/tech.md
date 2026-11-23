# 技術スタック（tech.md）

> AI はここに無い技術・ライブラリを勝手に導入しない。追加が必要なら ADR を提案する。

| 区分 | 採用技術 | バージョン方針 | 採用理由 / ADR |
|---|---|---|---|
| フロント FW | Vue 3（Composition API） | 3.x 最新 | |
| フロント言語 | TypeScript | 5.x, strict | |
| 状態管理 | Pinia | 最新 | |
| ルーティング | Vue Router | 4.x | |
| ビルド | Vite | 最新安定版 | |
| バック言語 | Java | 21（LTS） | |
| バック FW | Spring Boot | 3.x | |
| DB アクセス | Spring JDBC（JdbcClient） | Spring Boot 同梱 | TODO: MyBatis / JPA を使う場合は ADR |
| DB | PostgreSQL | 16 | |
| マイグレーション | Flyway | Spring Boot 管理 | |
| API 契約 | OpenAPI | 3.1 | コントラクトファースト |
| テスト | Vitest / JUnit 5 / Mockito / Testcontainers / Playwright | 最新安定版 | |
| 実行基盤 | ECS Fargate + ALB + RDS for PostgreSQL | - | |
| IaC | AWS CDK（TypeScript） | v2 | |
| CI/CD | GitHub Actions（TODO: CodePipeline を使う場合は ADR） | - | |

## 技術的な制約

- 依存ライブラリは EOL とライセンスを確認してから追加する。
- Java と Node のバージョンは LTS を使い、EOL の 6 か月前までに更新計画を立てる。
