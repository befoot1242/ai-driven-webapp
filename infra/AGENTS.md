# infra/AGENTS.md

`infra/` 配下で作業するときは、ルートの `AGENTS.md` に加えてこのファイルに従う。

## 想定する構成（基本設計で確定させる）

- ネットワーク: VPC（パブリック / プライベートサブネット、2 AZ 以上）
- 画面: S3 + CloudFront（または ALB 配下のコンテナで配信。ADR で決定）
- API: ECS Fargate + ALB
- DB: RDS for PostgreSQL（Multi-AZ は NFR-AVL に従う）
- 秘密情報: Secrets Manager
- 監視: CloudWatch Logs / メトリクス / アラーム、Security Hub

## 構成

```
infra/
├─ modules/      # 再利用する Construct（network, api-service, database など）
└─ envs/
   ├─ dev/       # 開発（AI が変更してよい）
   ├─ stg/       # 検証（変更は提案のみ）
   └─ prod/      # 本番（AI は変更しない）
```

## 禁止事項

- `envs/prod/` のファイルを変更すること（差分の提案のみ可）
- `cdk deploy` / `cdk destroy` など実環境に反映するコマンドの実行（`cdk synth` / `cdk diff` は可）
- アクセスキー、パスワード、アカウント ID をファイルに書くこと
- RDS・S3 など、データを持つリソースの `removalPolicy` を `DESTROY` にすること
- セキュリティグループで `0.0.0.0/0` からの受信を ALB 以外に許可すること
