---
applyTo: "**/{test,tests,e2e}/**,**/*.{test,spec}.ts,**/*Test.java"
---

# テストルール

## 共通

- テストはテスト設計（`TS-NNN`）に基づいて書き、テスト名またはコメントに `@trace TS-NNN` を書く。
- テスト設計に無いテストを追加した場合は、テスト設計への追記を提案する。
- テストを通すためにテスト対象コードや期待値を書き換えない。失敗したら原因を報告する。
- `skip` / `only` / `@Disabled` を残さない。

## フロントエンド（Vitest、`frontend/src/**/*.test.ts`）

- ストアと composables を中心にテストする。API は `api/` の関数をモックする。
- コンポーネントテストは表示と操作の結果を確認する（内部実装に依存しない）。

## バックエンド

- 単体（JUnit 5 + Mockito）: Service の業務ロジック。Repository をモックする。
- 結合（`@SpringBootTest` + Testcontainers の PostgreSQL）: Repository と API の入出力。H2 など別の DB で代用しない。
- テスト名は日本語の `@DisplayName` で観点を書く。

## E2E（Playwright、`e2e/`）

- 業務シナリオ単位で書く。セレクタは `data-testid`。
- 固定の待ち時間（`waitForTimeout`）は禁止。
- テストデータは各テストで用意し、他のテストに依存しない。
