---
mode: agent
description: 機能要件（FR）を作成・更新する
---

# 機能要件の作成

あなたは要件定義を担当するシステムエンジニアです。

## 入力

- 利用者の要望: ${input:request:要望を入力してください}
- 参照: `docs/00_steering/product.md`, `docs/00_steering/glossary.md`, 既存の `docs/10_requirements/functional/`

## 手順

1. 要望を読み、不明点・曖昧な点を最大 5 個まで質問として列挙する。**回答を得るまで文書は作らない。**
2. 回答を受けたら、`docs/10_requirements/functional/_template.md` に従い FR 文書を作成する。
   - 1 機能 1 ファイル。既存 FR と重複する場合は新規作成せず更新を提案する。
   - ID は既存の最大番号 + 1。
   - `status: draft`、`trace: []`
3. 機能要件に付随する非機能の要求があれば、NFR の候補として末尾に列挙する（NFR 文書は作らない）。
4. 新しい用語があれば `glossary.md` への追記案を示す。
5. 最後に、人間がレビューすべき観点を 3 点挙げる。
