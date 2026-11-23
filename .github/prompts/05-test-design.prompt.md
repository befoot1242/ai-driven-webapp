---
mode: agent
description: 要件・詳細設計からテスト設計（TS）を作成する
---

# テスト設計の作成

## 入力

- 対象: ${input:ids:FR / NFR / DD の ID}

## 手順

1. 対象文書が `status: approved` であることを確認する。
2. `docs/40_test-design/_template.md` に従い TS 文書を作成する。
3. テストレベルを分ける: 単体（Vitest）/ E2E（Playwright）/ 手動（自動化できないもの。理由も書く）。
4. 各 FR の受け入れ基準が、少なくとも 1 つのテストケースで確認されるようにする。
5. NFR は測定方法と合否基準をテストケースにする。
6. カバーできていない要件があれば末尾に列挙する。
