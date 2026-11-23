---
id: TS-001
title: （サンプル）お知らせ一覧のテスト設計
status: draft
trace: [FR-001, DD-notice-001]
owner: "sample"
updated: 2026-11-22
---

# TS-001 （サンプル）お知らせ一覧のテスト設計

## 1. テスト対象と範囲

FR-001 の受け入れ基準と DD-notice-001。

## 2. テストケース

| No | レベル | 観点 | 前提 | 操作 | 期待結果 | 対応 ID |
|---|---|---|---|---|---|---|
| 1 | 単体（バック） | 異常 | - | `list(-1, 20, now)` | IllegalArgumentException | DD-notice-001 |
| 2 | 単体（バック） | 境界 | - | `list(0, 0, now)` / `list(0, 101, now)` | IllegalArgumentException | DD-notice-001 |
| 3 | 単体（バック） | 正常 | Repository が 2 件返す | `list(0, 20, now)` | 2 件、total=2 | DD-notice-001 |
| 4 | 単体（フロント） | 正常 | API が 2 件返す | store.fetch(0) | status=loaded、2 件 | DD-notice-001 |
| 5 | 単体（フロント） | 異常 | API が失敗 | store.fetch(0) | status=error | DD-notice-001 |
| 6 | 結合（バック） | 正常 | DB に公開済み 2 件・未来 1 件 | `GET /api/notices` | 2 件、新しい順 | FR-001 |
| 7 | E2E | 正常 | 同上 | トップ画面を開く | 2 件表示 | FR-001 |

## 3. 手動テストとする理由

該当なし

## 4. テストデータ

各テストで投入する。

## 5. カバーできていない要件

なし

## 変更履歴

| 日付 | 変更者 | 内容 |
|---|---|---|
| 2026-11-22 | sample | 初版 |
