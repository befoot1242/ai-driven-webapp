---
applyTo: "infra/**"
---

# IaC ルール（AWS CDK / TypeScript）

詳細な禁止事項は `infra/AGENTS.md` を参照すること。

- 再利用する構成は `infra/modules/` に Construct として作り、環境ごとの差分は `infra/envs/<env>/` のパラメータだけで表現する。
- 環境ごとの値（アカウント ID、ドメイン、サイズ）をコードに直書きしない。
- リソースには `Project` / `Env` / `Owner` タグを必ず付ける。
- IAM は最小権限。`*` のアクション・リソースは原則禁止（使う場合は ADR に理由を書く）。
- データを持つリソース（S3、DynamoDB、RDS など）は `removalPolicy: RETAIN` を既定にする。
- 変更を提案するときは `cdk diff` の結果を添える。
- 非機能要件（NFR-AVL / NFR-SEC / NFR-OPS）との対応を Construct のコメントに `@trace` で書く。
