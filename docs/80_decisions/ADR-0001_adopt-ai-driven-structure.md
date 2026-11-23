---
id: ADR-0001
title: AI 駆動開発のリポジトリ構成を採用する
status: draft
trace: []
owner: ""
updated: 2026-11-22
---

# ADR-0001 AI 駆動開発のリポジトリ構成を採用する

## 状況

AI エージェントに要件定義〜テストまでを任せるにあたり、成果物の置き場所・工程の順序・ルールの適用方法を統一する必要がある。

## 決定

| 要素 | 参考にしたもの |
|---|---|
| ルートの指示ファイル | AGENTS.md（Linux Foundation 傘下 Agentic AI Foundation の標準）— REF-001 |
| ツール固有のルール・プロンプト | GitHub Copilot の `.github/instructions`, `.github/prompts`, `.github/agents` — REF-002 |
| 前提文書（product / tech / structure） | Kiro の steering — REF-003 |
| 工程と承認ゲート | AWS AI-DLC — REF-004 |
| 機能単位の仕様 | GitHub Spec Kit — REF-005 |
| API 契約の正本 | OpenAPI Specification — REF-006 |

## 結果

- 良い点: 複数の AI ツールで同じ文書を使える。工程の承認状態がファイルで追える。API の形がフロントとバックで食い違わない。
- 注意点: front matter の運用が崩れるとトレーサビリティが機能しない → `npm run trace:check` を CI で実行する。

## 変更履歴

| 日付 | 変更者 | 内容 |
|---|---|---|
| 2026-11-22 | | 初版 |
