---
applyTo: "frontend/**"
---

# 実装ルール（フロントエンド / Vue 3）

## 着手前

- 対象の詳細設計が `approved` であることを確認する。
- ファイル先頭（`.vue` は `<script setup>` 内の先頭）に `// @trace DD-xxx` を書く。

## 構成

```
frontend/src/
├─ api/          # API 呼び出し。OpenAPI の型に合わせる。画面から fetch を直接呼ばない
├─ stores/       # Pinia ストア
├─ views/        # ルーティング単位の画面（XxxView.vue）
├─ components/   # 再利用する部品（XxxYyy.vue）
├─ composables/  # useXxx
├─ router/
└─ types/
```

## Vue

- Composition API + `<script setup lang="ts">` のみ。Options API は使わない。
- props / emits は型で定義する（`defineProps<{...}>()`）。
- `v-html` は禁止（XSS 対策）。使う場合は ADR で理由を残す。
- 画面に表示する文言・エラーメッセージは画面設計（BD）に合わせる。

## Pinia ストア

- ストアは Setup Store 形式（`defineStore('xxx', () => { ... })`）で書く。
- **状態の更新はストアの action 経由のみ**。コンポーネントから `store.xxx = ...` で直接書き換えない。
- API 呼び出しは action の中で `api/` の関数を呼ぶ。ストアの中で fetch を直接書かない。
- 画面をまたいで共有しない一時的な状態は、ストアではなくコンポーネント内の `ref` に置く。
- どの画面がどのストアを更新・参照するかを詳細設計に書く。

## TypeScript

- `any` 禁止。API の型は `api/` に集約する。
- 名前付き export を使う（`.vue` を除く）。
