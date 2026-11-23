---
applyTo: "docs/**"
---

# 設計書の書き方ルール

## front matter（必須）

すべての設計書（`_template.md` と `README.md` を除く）は先頭に次の front matter を持つ。

```yaml
---
id: FR-001                 # 一意の ID。ファイル名の先頭と一致させる
title: ログイン状態の表示
status: draft              # draft / review / approved / deprecated
trace: []                  # 上流 ID の配列。要件は空、BD は FR/NFR、DD は BD、TS は FR/NFR/DD
owner: ""                  # 責任者
updated: 2026-11-22
---
```

- ファイル名は `<id>_<短い英語名>.md`（例: `FR-001_login-status.md`）
- `status` を `approved` にできるのは人間だけ。AI は `review` までしか変更しない。
- `approved` の文書を AI が変更する場合は、`status` を `review` に戻し、変更理由を末尾の「変更履歴」に追記する。

## ID 規則

| 種別 | 形式 | 例 |
|---|---|---|
| 機能要件 | `FR-NNN` | `FR-001` |
| 非機能要件 | `NFR-<PERF/SEC/AVL/OPS/UX/CMP>-NNN` | `NFR-SEC-001` |
| 基本設計 | `BD-NNN` | `BD-003` |
| 詳細設計 | `DD-<機能名>-NNN` | `DD-login-002` |
| テスト設計 | `TS-NNN` | `TS-015` |
| ADR | `ADR-NNNN` | `ADR-0001` |
| 外部参照 | `REF-NNN` | `REF-001` |

## 文章ルール

- 各工程フォルダの `_template.md` の章立てに従う。章を勝手に削除しない（該当なしなら「該当なし」と書く）。
- 1 文書 1 テーマ。長くなる場合は分割し、ID を分ける。
- 曖昧語（「適宜」「なるべく」「高速に」など）を使わず、数値・条件で書く。
- 用語は `docs/00_steering/glossary.md` に合わせる。新しい用語は用語集に追加する。
- 外部仕様を根拠にする場合は `REF-NNN` を本文に書き、`docs/90_references/index.md` に登録する。
