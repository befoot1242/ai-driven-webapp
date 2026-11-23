---
applyTo: "backend/**"
---

# 実装ルール（バックエンド / Spring Boot）

## 着手前

- 対象の詳細設計が `approved` であることを確認する。
- クラスの Javadoc またはファイル先頭に `// @trace DD-xxx` を書く。

## パッケージ構成（機能単位 → 層）

```
com.example.app
├─ common/                 # 例外ハンドラ、共通設定
└─ <feature>/              # 例: notice
   ├─ XxxController.java   # 入出力の変換とバリデーションのみ
   ├─ XxxService.java      # 業務ロジック・トランザクション境界
   ├─ XxxRepository.java   # DB アクセス（JdbcClient）
   └─ Xxx.java             # ドメインモデル（record）
```

## ルール

- Controller にロジックを書かない。トランザクション（`@Transactional`）は Service に付ける。
- コンストラクタインジェクションのみ（フィールドに `@Autowired` を付けない）。
- リクエスト・レスポンスの形は `api/openapi.yaml` に合わせる。合わない場合は OpenAPI の変更を先に提案する。
- 入力チェックは Bean Validation（`@Valid`）。エラー応答は `common` の例外ハンドラで RFC 9457（Problem Details）形式に統一する。
- SQL はパラメータバインドのみ。文字列連結で SQL を組み立てない。
- ログに個人情報・秘密情報を出さない。
- 設定値は `application.yml` と環境変数で渡す。秘密情報は AWS Secrets Manager から取得する前提とし、ファイルに書かない。
