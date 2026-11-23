# infra

バックエンドが必要になった時点で、AWS CDK（TypeScript）のプロジェクトをここに初期化します。

```bash
cd infra
npx aws-cdk@2 init app --language typescript
```

初期化後、`modules/` と `envs/{dev,stg,prod}/` の構成に合わせて整理してください（`infra/AGENTS.md` 参照）。
