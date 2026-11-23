# リポジトリ構成規約（structure.md）

```
.
├─ AGENTS.md
├─ .github/
│  ├─ copilot-instructions.md
│  ├─ instructions/        # 実装・API・DB・テスト・IaC・設計書のルール（applyTo で自動適用）
│  ├─ prompts/             # 工程ごとの実行プロンプト（01〜06）
│  ├─ agents/              # レビュー担当など
│  └─ workflows/           # CI
├─ docs/
│  ├─ 00_steering/         # 全工程共通の前提
│  ├─ 10_requirements/{functional,non-functional}/
│  ├─ 20_basic-design/     # BD-NNN
│  ├─ 30_detail-design/<機能>/
│  ├─ 40_test-design/
│  ├─ 80_decisions/        # ADR
│  ├─ 90_references/       # 外部参照
│  └─ traceability.md      # 自動生成
├─ api/
│  └─ openapi.yaml         # API 契約の正本
├─ frontend/               # Vue 3 + Vite
│  └─ src/{api,stores,views,components,composables,router,types}/
├─ backend/                # Spring Boot（Gradle）
│  └─ src/
│     ├─ main/java/com/example/app/<feature>/
│     ├─ main/resources/db/migration/   # Flyway
│     └─ test/java/...
├─ e2e/                    # Playwright（画面と API をまたぐシナリオ）
├─ infra/                  # AWS CDK
│  ├─ AGENTS.md
│  ├─ modules/
│  └─ envs/{dev,stg,prod}/
├─ compose.yaml            # ローカル開発用 PostgreSQL
└─ scripts/trace.mjs       # トレーサビリティ表の生成
```

## ルールの置き場所の原則

- **AI に守らせたいルールは `.github/instructions/` が正本。** `docs/` に同じことを書かない。
- **何を作るか（What）は `docs/`、どう作るか（How）は `.github/instructions/`。**
- **API の形は `api/openapi.yaml`、DB の形はマイグレーションが正本。** 設計書には意図と業務上の意味を書き、型や桁の一覧を二重管理しない。
