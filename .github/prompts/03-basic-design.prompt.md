---
mode: agent
description: 承認済みの要件から基本設計（BD）を作成する（Web アプリ）
---

# 基本設計の作成

## 入力

- 対象要件: ${input:ids:FR/NFR の ID（カンマ区切り）}

## 手順

1. 対象の FR / NFR がすべて `status: approved` であることを確認する。そうでなければ止めて報告する。
2. `docs/20_basic-design/_template.md` に従い BD 文書を作成する。`trace` に要件 ID を入れる。
3. 必ず次を含める。
   - 画面一覧・画面遷移・画面項目
   - API 一覧（メソッド、パス、概要、認可）
   - テーブル一覧と主要な列
   - 認証・認可の方針、権限ごとに操作できる範囲
   - 非機能要件への対応（性能目標、可用性構成、ログ・監視）
4. API の入出力を決めたら、`api/openapi.yaml` の変更案を作成する（BD が approved になってから反映する）。
5. 技術的な選択肢が複数ある判断は `docs/80_decisions/` に ADR 案を作成する。
6. 外部仕様に依存する箇所は `REF-NNN` を付け、`docs/90_references/index.md` に追記する。
7. 要件で満たせていない点・矛盾点を末尾に列挙する。
