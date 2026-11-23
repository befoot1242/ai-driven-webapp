---
mode: agent
description: 承認済みの詳細設計・テスト設計に基づいて実装とテストを行う（Web アプリ）
---

# 実装

## 入力

- 対象詳細設計: ${input:dd:DD の ID}
- 対象テスト設計: ${input:ts:TS の ID}

## 手順

1. DD と TS が `status: approved` であることを確認する。そうでなければ止める。
2. 実装計画を先に示し、確認を得る。順番は次の通り。
   1. `api/openapi.yaml`（変更がある場合）
   2. DB マイグレーション（新しい `V*.sql` を追加）
   3. バックエンド（Repository → Service → Controller）
   4. フロントエンド（api → stores → views/components）
3. 各層でテストを先に書き、失敗を確認してから実装する。
4. 次のコマンドがすべて通るまで修正する。期待値を書き換えて通すことは禁止。
   - `cd backend && ./gradlew test`
   - `cd frontend && npm run lint && npm run test`
5. リポジトリ直下で `npm run trace` を実行する。
6. DD と実装に差異が出た場合は DD の修正案を示す。
